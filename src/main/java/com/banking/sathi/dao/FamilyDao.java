package com.banking.sathi.dao;

import com.banking.sathi.model.Family;
import com.banking.sathi.repository.FamilyRepository;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FamilyDao implements FamilyRepository {
    private static final Logger logger = Logger.getLogger(FamilyDao.class.getName());

    @Override
    public int saveFamily(Family family, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_FAMILY_QUERY);
        ) {
            ps.setLong(1, family.getUserId());
            ps.setString(2, family.getFather());
            ps.setString(3, family.getMother());
            return ps.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to save family", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Family findById(Long id, Connection con) {
        return null;
    }

    @Override
    public boolean existsByUserId(Long userId, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_FAMILY_BY_USERID);
        ) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to check family by user id", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteByUserId(Long userId, Connection con) {

        try (PreparedStatement ps = con.prepareStatement(QueryUtil.DELETE_FAMILY_BY_USERID)) {
            ps.setLong(1, userId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);

        }
        return false;
    }
}
