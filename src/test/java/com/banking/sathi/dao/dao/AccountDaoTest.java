package com.banking.sathi.dao.dao;

import com.banking.sathi.dao.AccountDao;
import com.banking.sathi.enums.AccountStatus;
import com.banking.sathi.enums.AccountType;
import com.banking.sathi.model.Account;
import com.banking.sathi.utils.AccountNumberGenerator;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.TransactionPinGenerator;

import java.sql.SQLException;

public class AccountDaoTest {
    public static void main(String[] args) throws SQLException {
        AccountDao accountDao = new AccountDao();
        String accountNumber = AccountNumberGenerator.generateUniqueAccountNumber();
        String transactionPin = TransactionPinGenerator.generateTransactionPin();
        System.out.println(accountNumber);
        System.out.println(transactionPin);
        Account account = new Account(
                1L,
                accountNumber,
                transactionPin,
                AccountType.SAVINGS,
                5000.00,
                AccountStatus.INACTIVE
        );
        int rowsUpdates = accountDao.saveAccount(account, DbConnection.getConnection());
        System.out.println(rowsUpdates);

    }
}
