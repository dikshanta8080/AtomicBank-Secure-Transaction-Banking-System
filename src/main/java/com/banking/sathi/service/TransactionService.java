package com.banking.sathi.service;

import com.banking.sathi.dao.AccountDao;
import com.banking.sathi.dao.TransactionDao;
import com.banking.sathi.enums.AccountStatus;
import com.banking.sathi.enums.TransactionStatus;
import com.banking.sathi.enums.TransactionType;
import com.banking.sathi.exceptions.AccountDoesNotExistsException;
import com.banking.sathi.exceptions.InvalidTransactionPinException;
import com.banking.sathi.model.Account;
import com.banking.sathi.model.Transaction;
import com.banking.sathi.repository.AccountRepository;
import com.banking.sathi.repository.TransactionRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.HashUtil;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionService {

    private static final Logger logger = Logger.getLogger(TransactionService.class.getName());
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService() {
        this.accountRepository = new AccountDao();
        this.transactionRepository = new TransactionDao();
    }

    public boolean transfer(Long fromUserId,
                            Long toUserId,
                            Double transferAmount,
                            String transactionPin) {

        Connection con = null;

        Transaction tx = new Transaction();

        try {
            con = DbConnection.getConnection();
            con.setAutoCommit(false);

            if (transferAmount == null || transferAmount <= 0) {
                throw new IllegalArgumentException("Transfer amount must be greater than 0");
            }
            if (fromUserId == null || toUserId == null || fromUserId.equals(toUserId)) {
                throw new IllegalArgumentException("Invalid transfer accounts");
            }

            Account fromAccount = accountRepository
                    .findByUserId(fromUserId, con)
                    .orElseThrow(() ->
                            new AccountDoesNotExistsException("Sender account not found"));

            Account toAccount = accountRepository
                    .findByUserId(toUserId, con)
                    .orElseThrow(() ->
                            new AccountDoesNotExistsException("Receiver account not found"));


            Account lockedFrom;
            Account lockedTo;

            if (fromAccount.getId() < toAccount.getId()) {
                lockedFrom = accountRepository.lockRowsForUpdate(con, fromAccount.getId());
                lockedTo = accountRepository.lockRowsForUpdate(con, toAccount.getId());
            } else {
                lockedTo = accountRepository.lockRowsForUpdate(con, toAccount.getId());
                lockedFrom = accountRepository.lockRowsForUpdate(con, fromAccount.getId());
            }

            tx.setFromAccountId(lockedFrom.getId());
            tx.setToAccountId(lockedTo.getId());
            tx.setAmount(transferAmount);
            tx.setType(TransactionType.TRANSFER);

            if (lockedFrom.getStatus() != AccountStatus.ACTIVE ||
                    lockedTo.getStatus() != AccountStatus.ACTIVE) {
                throw new RuntimeException("One of the accounts is inactive");
            }


            if (transactionPin == null || transactionPin.isBlank()) {
                throw new InvalidTransactionPinException("PIN is required");
            }

            if (!HashUtil.check(transactionPin, lockedFrom.getTransactionPin())) {
                throw new InvalidTransactionPinException("Invalid transaction PIN");
            }


            if (lockedFrom.getBalance() < transferAmount) {
                throw new IllegalArgumentException("Insufficient balance");
            }

            int withdrawRows = accountRepository.withdraw(lockedFrom.getId(), transferAmount, con);
            int depositRows = accountRepository.deposit(lockedTo.getId(), transferAmount, con);
            if (withdrawRows <= 0 || depositRows <= 0) {
                throw new RuntimeException("Transfer failed");
            }

            tx.setStatus(TransactionStatus.SUCCESS);
            tx.setRemarks("Transfer successful");

            transactionRepository.saveTransaction(tx, con);

            con.commit();
            return true;

        } catch (Exception e) {

            if (con != null) {
                try {
                    con.rollback();

                    con.setAutoCommit(true);

                    tx.setType(tx.getType() == null ? TransactionType.TRANSFER : tx.getType());
                    tx.setStatus(TransactionStatus.ROLLED_BACK);
                    tx.setRemarks(resolveErrorMessage(e));
                    tx.setAmount(tx.getAmount() == null ? (transferAmount == null ? 0d : transferAmount) : tx.getAmount());

                    transactionRepository.saveTransaction(tx, con);

                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Rollback logging failed", ex);
                }
            }

            throw new RuntimeException(e);

        } finally {

            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Connection close failed", e);
                }
            }
        }
    }

    private String resolveErrorMessage(Exception e) {
        if (e.getMessage() != null && !e.getMessage().isBlank()) {
            return e.getMessage();
        }
        return "Transaction failed";
    }
}
