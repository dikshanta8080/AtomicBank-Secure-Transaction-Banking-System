package com.banking.sathi.service;

import com.banking.sathi.dao.AccountDao;
import com.banking.sathi.dao.CardDao;
import com.banking.sathi.dao.KycDao;
import com.banking.sathi.dao.TransactionDao;
import com.banking.sathi.dto.response.AdminAccountRowDto;
import com.banking.sathi.dto.response.CardResponseDto;
import com.banking.sathi.dto.response.TransactionViewDto;
import com.banking.sathi.dto.response.TransferTargetDto;
import com.banking.sathi.enums.CardStatus;
import com.banking.sathi.enums.TransactionStatus;
import com.banking.sathi.model.Account;
import com.banking.sathi.repository.AccountRepository;
import com.banking.sathi.repository.CardRepository;
import com.banking.sathi.repository.KycRepository;
import com.banking.sathi.repository.TransactionRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PortalService {
    private static final Logger logger = Logger.getLogger(PortalService.class.getName());

    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final KycRepository kycRepository;
    private final TransactionRepository transactionRepository;
    private final InterestService interestService;

    public PortalService() {
        this.accountRepository = new AccountDao();
        this.cardRepository = new CardDao();
        this.kycRepository = new KycDao();
        this.transactionRepository = new TransactionDao();
        this.interestService = new InterestService();
    }

    public Optional<Account> getAccountByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    public List<CardResponseDto> getPendingCards() {
        return new CardService().getPendingApprovalCards();
    }

    public List<com.banking.sathi.model.Card> getCardsForUser(Long userId) {
        Optional<Account> account = accountRepository.findByUserId(userId);
        if (account.isEmpty()) {
            return Collections.emptyList();
        }
        return cardRepository.findCardByAccount(account.get().getId());
    }

    public double getInterestForUser(Long userId) {
        Optional<Account> account = accountRepository.findByUserId(userId);
        if (account.isEmpty()) {
            return 0d;
        }
        return interestService.calculateSimpleInterest(account.get().getId());
    }

    public Double getMonthlyIncomeForUser(Long userId) {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_MONTHLY_INCOME_BY_USER)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("income") / 12;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to load monthly income", e);
        }
        return 0d;
    }

    public List<TransferTargetDto> getTransferTargets(Long currentUserId) {
        List<TransferTargetDto> targets = new ArrayList<>();
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_TRANSFER_TARGETS)) {
            ps.setLong(1, currentUserId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TransferTargetDto dto = new TransferTargetDto();
                dto.setUserId(rs.getLong("user_id"));
                dto.setAccountId(rs.getLong("account_id"));
                dto.setUserName(rs.getString("name"));
                dto.setAccountNumber(rs.getString("account_number"));
                targets.add(dto);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to load transfer targets", e);
        }
        return targets;
    }

    public List<TransactionViewDto> getUserTransactions(Long userId, LocalDate fromDate, LocalDate toDate) {
        return transactionRepository.findByUserId(userId, fromDate, toDate);
    }

    public List<TransactionViewDto> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<TransactionViewDto> getFailedTransactions() {
        return transactionRepository.findFailed();
    }

    public int getTotalAccounts() {
        return accountRepository.getTotalNumberOfAccounts();
    }

    public int getPendingAccountsCount() {
        return accountRepository.getNumberOfPendingApprovals();
    }

    public List<AdminAccountRowDto> getAllAccountsForAdmin() {
        List<AdminAccountRowDto> accounts = new ArrayList<>();
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_ALL_ACCOUNTS_FOR_ADMIN);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AdminAccountRowDto dto = new AdminAccountRowDto();
                dto.setUserId(rs.getLong("user_id"));
                dto.setUserName(rs.getString("name"));
                dto.setEmail(rs.getString("email"));
                dto.setAccountNumber(rs.getString("account_number"));
                dto.setAccountType(rs.getString("account_type"));
                dto.setAccountStatus(rs.getString("account_status"));
                dto.setBalance(rs.getDouble("balance"));
                accounts.add(dto);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to load admin account list", e);
        }
        return accounts;
    }

    public int getTotalCards() {
        return cardRepository.findTotalNumberOfCards();
    }

    public int getPendingCardsCount() {
        return cardRepository.findNumberOfPendingApprovals();
    }

    public double getTotalDeposits() {
        return accountRepository.getTotalDeposits();
    }

    public long countTransactionsByStatus(TransactionStatus status) {
        return transactionRepository.countByStatus(status);
    }

    public long countTransactionsForUser(Long userId) {
        return transactionRepository.countByUserId(userId);
    }

    public long countCardsByStatusForUser(Long userId, CardStatus status) {
        Optional<Account> account = accountRepository.findByUserId(userId);
        if (account.isEmpty()) return 0;
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.COUNT_CARDS_BY_STATUS_FOR_USER)) {
            ps.setLong(1, account.get().getId());
            ps.setString(2, status.name());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getLong("total");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to count cards by status for user", e);
        }
        return 0;
    }
}
