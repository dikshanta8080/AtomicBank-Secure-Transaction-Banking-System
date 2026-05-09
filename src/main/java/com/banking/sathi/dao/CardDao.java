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
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.DELETE_CARD_QUERY);
        ) {
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to delete the card {e}", e);
        }
        return 0;
    }

    @Override
    public Optional<Card> findById(Long cardId) {
        Card card;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_CARD_BY_ID);
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                card = new Card();
                card.setId(rs.getLong("id"));
                card.setAccountId(rs.getLong("account_id"));
                card.setCardNumber(rs.getString("card_number"));
                card.setType(CardType.valueOf(rs.getString("type").toUpperCase()));
                card.setStatus(CardStatus.valueOf(rs.getString("status").toUpperCase()));
                card.setCreditLimit(rs.getDouble("credit_limit"));
                card.setExpiryDate(rs.getObject("expiry_date", LocalDate.class));
                card.setCvv(rs.getString("cvv"));
                card.setCreated(rs.getObject("created", LocalDateTime.class));
                return Optional.of(card);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve the card from database {e}", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Card> findAllCards() {
        Card card;
        List<Card> cards = new ArrayList<>();
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_ALL_CARDS);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                card = new Card();
                card.setId(rs.getLong("id"));
                card.setAccountId(rs.getLong("account_id"));
                card.setCardNumber(rs.getString("card_number"));
                card.setType(CardType.valueOf(rs.getString("type").toUpperCase()));
                card.setStatus(CardStatus.valueOf(rs.getString("status").toUpperCase()));
                card.setCreditLimit(rs.getDouble("credit_limit"));
                card.setExpiryDate(rs.getObject("expiry_date", LocalDate.class));
                card.setCvv(rs.getString("cvv"));
                card.setCreated(rs.getObject("created", LocalDateTime.class));
                cards.add(card);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve all the cards {e}", e);
        }
        return cards;
    }

    @Override
    public List<CardResponseDto> getPendingApprovalCards() {
        CardResponseDto card;
        List<CardResponseDto> cardResponseDtos = new ArrayList<>();
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_PENDING_APPROVAL_CARDS);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                card = new CardResponseDto();
                card.setId(rs.getLong("id"));
                card.setAccountId(rs.getLong("account_id"));
                card.setCardNumber(rs.getString("card_number"));
                card.setType(CardType.valueOf(rs.getString("type").toUpperCase()));
                card.setStatus(CardStatus.valueOf(rs.getString("status").toUpperCase()));
                card.setCreditLimit(rs.getDouble("credit_limit"));
                card.setExpiryDate(rs.getObject("expiry_date", LocalDate.class));
                card.setAppliedDate(rs.getObject("created", LocalDateTime.class));
                cardResponseDtos.add(card);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to get the pending approvals");
        }
        return cardResponseDtos;
    }

    @Override
    public Optional<CardResponseDto> findPendingCard(Long cardId) {
        CardResponseDto card;

        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_CARD_BY_ID);
        ) {
            ps.setLong(1, cardId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                card = new CardResponseDto();
                card.setId(rs.getLong("id"));
                card.setAccountId(rs.getLong("account_id"));
                card.setCardNumber(rs.getString("card_number"));
                card.setType(CardType.valueOf(rs.getString("type").toUpperCase()));
                card.setStatus(CardStatus.valueOf(rs.getString("status").toUpperCase()));
                card.setCreditLimit(rs.getDouble("credit_limit"));
                card.setExpiryDate(rs.getObject("expiry_date", LocalDate.class));
                card.setAppliedDate(rs.getObject("created", LocalDateTime.class));
                return Optional.of(card);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retreve the card, {e}", e);
        }
        return Optional.empty();
    }

    @Override
    public int findNumberOfPendingApprovals() {
        int pendingApprovals = 0;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_NUMBER_OF_PENDING_CARD_APPROVALS);
                ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) pendingApprovals = rs.getInt("numberOfPendingCard");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrive the number of approvals");
        }
        return pendingApprovals;
    }

    @Override
    public int findTotalNumberOfCards() {
        int totalCards = 0;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_TOTAL_NUMBER_OF_CARDS);
                ResultSet rs = ps.executeQuery();

        ) {

            if (rs.next()) totalCards = rs.getInt("totalCards");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrive the number of approvals");
        }
        return totalCards;
    }

    @Override
    public List<Card> findCardByAccount(Long accountId) {
        Card card;
        List<Card> cards = new ArrayList<>();
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_CARD_BY_ACCOUNT);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                card = new Card();
                card.setId(rs.getLong("id"));
                card.setAccountId(rs.getLong("account_id"));
                card.setCardNumber(rs.getString("card_number"));
                card.setType(CardType.valueOf(rs.getString("type").toUpperCase()));
                card.setStatus(CardStatus.valueOf(rs.getString("status").toUpperCase()));
                card.setCreditLimit(rs.getDouble("credit_limit"));
                card.setExpiryDate(rs.getObject("expiry_date", LocalDate.class));
                card.setCvv(rs.getString("cvv"));
                card.setCreated(rs.getObject("created", LocalDateTime.class));
                cards.add(card);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retreve the cards {e}", e);
        }
        return cards;
    }

    @Override
    public boolean verifyCard(Long cardId) {
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.VERIFY_CARD);
        ) {
            ps.setLong(1, cardId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to verify the card");
        }
        return false;
    }

    @Override
    public boolean rejectCard(Long cardId) {
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.REJECT_CARD);
        ) {
            ps.setLong(1, cardId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to reject the card");
        }
        return false;
    }
}


