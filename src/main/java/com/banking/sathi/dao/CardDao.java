package com.banking.sathi.dao;

import com.banking.sathi.dto.response.CardResponseDto;
import com.banking.sathi.model.Card;
import com.banking.sathi.repository.CardRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardDao implements CardRepository {
    private static final Logger logger = Logger.getLogger(CardDao.class.getName());

    @Override
    public int saveCard(Card card) {
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_CARD_QUERY);
        ) {
            ps.setLong(1, card.getAccountId());
            ps.setString(2, card.getCardNumber());
            ps.setString(3, card.getType().name());
            ps.setString(4, card.getStatus().name());
            ps.setDouble(5, card.getCreditLimit());
            ps.setDate(6, Date.valueOf(card.getExpiryDate()));
            ps.setString(7, card.getCvv());
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to insert card in the database ,{e}", e);
        }
        return 0;

    }

    @Override
    public int deleteCard(Long cardId) {
        return 0;
    }

    @Override
    public Optional<Card> findById(Long cardId) {
        return Optional.empty();
    }

    @Override
    public List<Card> findAllCards() {
        return List.of();
    }

    @Override
    public List<CardResponseDto> getPendingApprovalCards() {
        return List.of();
    }

    @Override
    public Optional<CardResponseDto> findPendingCard(Long cardId) {
        return Optional.empty();
    }

    @Override
    public int findNumberOfPendingApprovals() {
        int pendingApprovals = 0;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_NUMBER_OF_PENDING_CARD_APPROVALS)
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) pendingApprovals = rs.getInt("numberOfPendingCard");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrive the number of approvals");
        }
        return pendingApprovals;
    }

    @Override
    public int findTotalNumberIfCards() {
        return 0;
    }

    @Override
    public List<Card> findCardByAccount(Long accountId) {
        return List.of();
    }
}
