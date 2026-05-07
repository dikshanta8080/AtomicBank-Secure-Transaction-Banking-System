package com.banking.sathi.dao;

import com.banking.sathi.model.Card;
import com.banking.sathi.repository.CardRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
