package com.banking.sathi.repository;

import com.banking.sathi.model.Account;

import java.sql.Connection;

public interface AccountRepository {
    int saveAccount(Account account, Connection con);

    Account findById(Long id, Connection con);
}
