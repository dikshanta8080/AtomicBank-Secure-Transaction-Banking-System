package com.banking.sathi.dao;

import com.banking.sathi.repository.InterestRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterestDao implements InterestRepository {
    private static final Logger logger = Logger.getLogger(InterestDao.class.getName());

    @Override
    public Double findBalanceByAccountId(Long accountId) {
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_BALANCE_BY_ACCOUNT_ID);
        ) {
            ps.setLong(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("balance");
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to fetch account balance", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void applyInterest(Long accountId, Double rate, Double amount) {
        Connection con = null;
        try {
            con = DbConnection.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement insertInterest = con.prepareStatement(QueryUtil.INSERT_INTEREST_QUERY);
                 PreparedStatement updateBalance = con.prepareStatement(QueryUtil.DEPOSIT_MONEY)) {

                insertInterest.setLong(1, accountId);
                insertInterest.setDouble(2, rate);
                insertInterest.setDouble(3, amount);
                int rowsInserted = insertInterest.executeUpdate();
                if (rowsInserted == 0) {
                    throw new SQLException("Failed to insert interest data.");
                }


                updateBalance.setDouble(1, amount);
                updateBalance.setLong(2, accountId);
                int rowsUpdated = updateBalance.executeUpdate();
                if (rowsUpdated == 0) {
                    throw new SQLException("Failed to update account balance.");
                }

                con.commit();
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, "Rollback failed", ex);
                }
            }
            logger.log(Level.SEVERE, "Failed to apply interest", e);
            throw new RuntimeException("Error applying interest", e);
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Failed to close connection", e);
                }
            }
        }
    }
}
