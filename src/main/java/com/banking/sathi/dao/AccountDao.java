package com.banking.sathi.dao;

import com.banking.sathi.model.Account;
import com.banking.sathi.repository.AccountRepository;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDao implements AccountRepository {
    private static final Logger logger = Logger.getLogger(AccountDao.class.getName());

    @Override
    public int saveAccount(Account account, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_ACCOUNT_QUERY);

        ) {
            ps.setLong(1, account.getUserId());
            ps.setString(2, account.getAccountNumber());
            ps.setString(3, account.getTransactionPin());
            ps.setString(4, account.getType().name());
            ps.setDouble(5, account.getBalance());
            ps.setString(6, account.getStatus().name());
            return ps.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to execute the query {e}", e);
        }
        return 0;
    }

    @Override
    public Account findById(Long id, Connection con) {
        return null;
    }

    @Override
    public boolean existsByUserId(Long userId, Connection con) {
        return false;
    }
}
