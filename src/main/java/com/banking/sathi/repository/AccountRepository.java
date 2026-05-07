package com.banking.sathi.repository;

import com.banking.sathi.dto.response.AccountDetailDTO;
import com.banking.sathi.dto.response.AccountListDTO;
import com.banking.sathi.model.Account;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    int saveAccount(Account account, Connection con);

    Account findById(Long id, Connection con);

    boolean existsByUserId(Long userId, Connection con);

    int deleteAccountById(Long accountId);

    boolean verifyAccount(Long accountId, Connection con);

    Optional<Account> findByUserId(Long userId, Connection con);

    List<AccountListDTO> getPendingAccounts();

    AccountDetailDTO getDetailedPendingApproval(Long userId);

    boolean existsByUserId(Long userId);

    boolean deleteByUserId(Long userId, Connection con);

    Optional<Account> findByUserId(Long userId);

    boolean freezeAccount(Long userId);

    int getNumberOfPendingApprovals();

    int getTotalNumberOfAccounts();

    double getTotalDeposits();

    double getBalanceByUserId(Long userId);
}
