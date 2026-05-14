package com.banking.sathi.dao;

import com.banking.sathi.dto.response.TransactionViewDto;
import com.banking.sathi.enums.TransactionStatus;
import com.banking.sathi.enums.TransactionType;
import com.banking.sathi.repository.TransactionRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.banking.sathi.model.Transaction;

public class TransactionDao implements TransactionRepository {
    private static final Logger logger = Logger.getLogger(TransactionDao.class.getName());

    @Override
    public int saveTransaction(Transaction tx, Connection con) {
        try (PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_TRANSACTION_QUERY)) {
            if (tx.getFromAccountId() != null) ps.setLong(1, tx.getFromAccountId());
            else ps.setNull(1, java.sql.Types.BIGINT);
            if (tx.getToAccountId() != null) ps.setLong(2, tx.getToAccountId());
            else ps.setNull(2, java.sql.Types.BIGINT);
            ps.setString(3, tx.getType().name());
            ps.setString(4, tx.getStatus().name());
            ps.setDouble(5, tx.getAmount());
            ps.setString(6, tx.getRemarks());
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to save transaction", e);
        }
        return 0;
    }

    @Override
    public List<TransactionViewDto> findByUserId(Long userId, LocalDate fromDate, LocalDate toDate) {
        String sql;
        if (fromDate != null && toDate != null) {
            sql = QueryUtil.SELECT_TRANSACTIONS_BY_USER_DATE_RANGE;
        } else if (fromDate != null) {
            sql = QueryUtil.SELECT_TRANSACTIONS_BY_USER_FROM_DATE;
        } else if (toDate != null) {
            sql = QueryUtil.SELECT_TRANSACTIONS_BY_USER_TO_DATE;
        } else {
            sql = QueryUtil.SELECT_TRANSACTIONS_BY_USER;
        }

        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            int idx = 1;
            ps.setLong(idx++, userId);
            ps.setLong(idx++, userId);
            if (fromDate != null) ps.setDate(idx++, Date.valueOf(fromDate));
            if (toDate != null) ps.setDate(idx, Date.valueOf(toDate));
            return mapTransactions(ps.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to load user transactions", e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<TransactionViewDto> findAll() {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_ALL_TRANSACTIONS)) {
            return mapTransactions(ps.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to load all transactions", e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<TransactionViewDto> findFailed() {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_FAILED_TRANSACTIONS)) {
            return mapTransactions(ps.executeQuery());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to load failed transactions", e);
        }
        return new ArrayList<>();
    }

    @Override
    public long countByStatus(TransactionStatus status) {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.COUNT_TRANSACTIONS_BY_STATUS)) {
            ps.setString(1, status.name());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getLong("total");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to count transactions by status", e);
        }
        return 0;
    }

    @Override
    public long countByUserId(Long userId) {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.COUNT_TRANSACTIONS_FOR_USER)) {
            ps.setLong(1, userId);
            ps.setLong(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getLong("total");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to count user transactions", e);
        }
        return 0;
    }

    private List<TransactionViewDto> mapTransactions(ResultSet rs) throws SQLException {
        List<TransactionViewDto> list = new ArrayList<>();
        while (rs.next()) {
            TransactionViewDto dto = new TransactionViewDto();
            dto.setId(rs.getLong("id"));
            dto.setReference(String.format("TXN-%06d", rs.getLong("id")));
            long fromId = rs.getLong("from_account_id");
            dto.setFromAccountId(rs.wasNull() ? null : fromId);
            long toId = rs.getLong("to_account_id");
            dto.setToAccountId(rs.wasNull() ? null : toId);
            dto.setFromAccountNumber(rs.getString("from_account_number"));
            dto.setToAccountNumber(rs.getString("to_account_number"));
            dto.setFromUserName(rs.getString("from_user_name"));
            dto.setToUserName(rs.getString("to_user_name"));
            dto.setType(TransactionType.valueOf(rs.getString("type")));
            dto.setStatus(TransactionStatus.valueOf(rs.getString("status")));
            dto.setAmount(rs.getDouble("amount"));
            dto.setRemarks(rs.getString("remarks"));
            Timestamp created = rs.getTimestamp("created");
            dto.setCreated(created != null ? created.toLocalDateTime() : LocalDateTime.now());
            list.add(dto);
        }
        return list;
    }
}
