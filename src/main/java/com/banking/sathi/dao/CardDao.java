package com.banking.sathi.dao;

import com.banking.sathi.dto.response.CardResponseDto;
import com.banking.sathi.enums.CardStatus;
import com.banking.sathi.enums.CardType;
import com.banking.sathi.model.Card;
import com.banking.sathi.repository.CardRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardDao implements CardRepository {
    private static final Logger logger = Logger.getLogger(CardDao.class.getName());

    @Override
    public int saveCard(Card card, Connection con) {
        try (PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_CARD_QUERY)) {
            ps.setLong(1, card.getAccountId());
            ps.setString(2, card.getCardNumber());
            ps.setString(3, card.getType().name());
            ps.setString(4, card.getStatus().name());
            ps.setDouble(5, card.getCreditLimit());
            ps.setDate(6, Date.valueOf(card.getExpiryDate()));
            ps.setString(7, card.getCvv());
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to insert card", e);
        }
        return 0;
    }

    @Override
    public int deleteCard(Long cardId) {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.DELETE_CARD_QUERY)) {
            ps.setLong(1, cardId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to delete the card", e);
        }
        return 0;
    }

    @Override
    public Optional<Card> findById(Long cardId) {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_CARD_BY_ID)) {
            ps.setLong(1, cardId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapCard(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve the card", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Card> findAllCards() {
        List<Card> cards = new ArrayList<>();
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_ALL_CARDS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cards.add(mapCard(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve all cards", e);
        }
        return cards;
    }

    @Override
    public List<CardResponseDto> getPendingApprovalCards() {
        List<CardResponseDto> dtos = new ArrayList<>();
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_PENDING_APPROVAL_CARDS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CardResponseDto dto = new CardResponseDto();
                dto.setId(rs.getLong("id"));
                dto.setAccountId(rs.getLong("account_id"));
                dto.setCardNumber(rs.getString("card_number"));
                dto.setType(CardType.valueOf(rs.getString("type").toUpperCase()));
                dto.setStatus(CardStatus.valueOf(rs.getString("status").toUpperCase()));
                dto.setCreditLimit(rs.getDouble("credit_limit"));
                dto.setExpiryDate(rs.getObject("expiry_date", LocalDate.class));
                dto.setAppliedDate(rs.getObject("created", LocalDateTime.class));
                dto.setUserName(rs.getString("user_name"));
                dto.setAccountNumber(rs.getString("account_number"));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to get pending approval cards", e);
        }
        return dtos;
    }

    @Override
    public Optional<CardResponseDto> findPendingCard(Long cardId) {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_CARD_BY_ID)) {
            ps.setLong(1, cardId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CardResponseDto dto = new CardResponseDto();
                dto.setId(rs.getLong("id"));
                dto.setAccountId(rs.getLong("account_id"));
                dto.setCardNumber(rs.getString("card_number"));
                dto.setType(CardType.valueOf(rs.getString("type").toUpperCase()));
                dto.setStatus(CardStatus.valueOf(rs.getString("status").toUpperCase()));
                dto.setCreditLimit(rs.getDouble("credit_limit"));
                dto.setExpiryDate(rs.getObject("expiry_date", LocalDate.class));
                dto.setAppliedDate(rs.getObject("created", LocalDateTime.class));
                return Optional.of(dto);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve the card", e);
        }
        return Optional.empty();
    }

    @Override
    public int findNumberOfPendingApprovals() {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_NUMBER_OF_PENDING_CARD_APPROVALS);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt("numberOfPendingCard");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve pending approvals count", e);
        }
        return 0;
    }

    @Override
    public int findTotalNumberOfCards() {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_TOTAL_NUMBER_OF_CARDS);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt("totalCards");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve total cards count", e);
        }
        return 0;
    }

    @Override
    public List<Card> findCardByAccount(Long accountId) {
        List<Card> cards = new ArrayList<>();
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_CARD_BY_ACCOUNT)) {
            ps.setLong(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cards.add(mapCard(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve cards by account", e);
        }
        return cards;
    }

    @Override
    public boolean verifyCard(Long cardId) {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.VERIFY_CARD)) {
            ps.setLong(1, cardId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to verify the card", e);
        }
        return false;
    }

    @Override
    public boolean rejectCard(Long cardId) {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.REJECT_CARD)) {
            ps.setLong(1, cardId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to reject the card", e);
        }
        return false;
    }

    @Override
    public boolean issueCard(Long cardId, Double creditLimit, LocalDate expiryDate) {
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(QueryUtil.ISSUE_CARD)) {
            ps.setDouble(1, creditLimit);
            ps.setDate(2, Date.valueOf(expiryDate));
            ps.setLong(3, cardId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to issue the card", e);
        }
        return false;
    }

    private Card mapCard(ResultSet rs) throws SQLException {
        Card card = new Card();
        card.setId(rs.getLong("id"));
        card.setAccountId(rs.getLong("account_id"));
        card.setCardNumber(rs.getString("card_number"));
        card.setType(CardType.valueOf(rs.getString("type").toUpperCase()));
        card.setStatus(CardStatus.valueOf(rs.getString("status").toUpperCase()));
        card.setCreditLimit(rs.getDouble("credit_limit"));
        card.setExpiryDate(rs.getObject("expiry_date", LocalDate.class));
        card.setCvv(rs.getString("cvv"));
        card.setRejectionReason(rs.getString("rejection_reason"));
        card.setCreated(rs.getObject("created", LocalDateTime.class));
        return card;
    }
}
