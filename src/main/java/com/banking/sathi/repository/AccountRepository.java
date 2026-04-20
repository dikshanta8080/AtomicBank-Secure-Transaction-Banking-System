package com.banking.sathi.repository;

import com.banking.sathi.model.Account;

import java.sql.Connection;

public interface AccountRepository {
    boolean saveAccount(Account account, Connection con);

    boolean findById(Long id, Connection con);
}
