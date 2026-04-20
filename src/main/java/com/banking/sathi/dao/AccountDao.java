package com.banking.sathi.dao;

import com.banking.sathi.model.Account;
import com.banking.sathi.repository.AccountRepository;

import java.sql.Connection;

public class AccountDao implements AccountRepository {

    @Override
    public boolean saveAccount(Account account, Connection con) {
        return false;
    }

    @Override
    public boolean findById(Long id, Connection con) {
        return false;
    }
}
