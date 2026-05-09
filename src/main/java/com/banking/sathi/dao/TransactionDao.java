package com.banking.sathi.dao;

import com.banking.sathi.model.Transaction;
import com.banking.sathi.repository.TransactionRepository;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TransactionDao implements TransactionRepository {


    @Override
    public boolean saveTransaction(Transaction transaction, Connection con) {

        try (
                PreparedStatement ps =
                        con.prepareStatement(QueryUtil.INSERT_TRANSACTION_QUERY);
        ) {

            if (transaction.getFromAccountId() != null) {
                ps.setLong(1, transaction.getFromAccountId());
            } else {
                ps.setNull(1, Types.BIGINT);
            }


            if (transaction.getToAccountId() != null) {
                ps.setLong(2, transaction.getToAccountId());
            } else {
                ps.setNull(2, Types.BIGINT);
            }
            ps.setString(3, transaction.getType().name());
            ps.setString(4, transaction.getStatus().name());
            ps.setDouble(5, transaction.getAmount());
            ps.setString(6, transaction.getRemarks());

            int rowsInserted = ps.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
