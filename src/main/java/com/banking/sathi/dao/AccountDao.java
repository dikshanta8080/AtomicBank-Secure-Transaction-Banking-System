package com.banking.sathi.dao;

import com.banking.sathi.enums.AccountStatus;
import com.banking.sathi.enums.AccountType;
import com.banking.sathi.model.Account;
import com.banking.sathi.repository.AccountRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_FAMILY_BY_USERID);
        ) {
            ps.setLong(1, userId);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return false;
    }

    @Override
    public int deleteAccountById(Long accountId) {
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.DELETE_ACCOUNT_QUERY);
        ) {
            ps.setLong(1, accountId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return 0;
    }

    @Override
    public boolean verifyAccount(Long accountId, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.VERIFY_ACCOUNT_QUERY);
        ) {
            ps.setLong(1, accountId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return false;
    }


    @Override
    public Optional<Account> findByUserId(Long userId, Connection con) {
        Account account;
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_BY_USERID_QUERY);
        ) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            account = new Account();
            account.setId(rs.getLong("id"));
            account.setUserId(rs.getLong("user_id"));
            account.setType(AccountType.valueOf(rs.getString("account_type").toUpperCase()));
            account.setStatus(AccountStatus.valueOf(rs.getString("status").toUpperCase()));
            account.setAccountNumber(rs.getString("account_number"));
            account.setTransactionPin(rs.getString("transaction_pin"));
            account.setBalance(rs.getDouble("balance"));
            return Optional.of(account);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return Optional.empty();
    }
}
