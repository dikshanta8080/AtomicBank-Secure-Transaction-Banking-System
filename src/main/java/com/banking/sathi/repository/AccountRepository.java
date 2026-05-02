package com.banking.sathi.repository;

import com.banking.sathi.model.Account;

import java.sql.Connection;
import java.util.Optional;

public interface AccountRepository {
    int saveAccount(Account account, Connection con);

    Account findById(Long id, Connection con);

    boolean existsByUserId(Long userId, Connection con);

    int deleteAccountById(Long accountId);

    boolean verifyAccount(Long accountId, Connection con);

    Optional<Account> findByUserId(Long userId, Connection con);
}
