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
}
