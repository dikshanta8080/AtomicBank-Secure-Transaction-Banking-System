package com.banking.sathi.dao;

import com.banking.sathi.model.Kyc;
import com.banking.sathi.repository.KycRepository;
import com.banking.sathi.utils.QueryUtil;

import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KycDao implements KycRepository {
    private static final Logger logger = Logger.getLogger(KycDao.class.getName());

    @Override
    public int saveKyc(Kyc kyc, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_KYC_QUERY);
        ) {
            ps.setLong(1, kyc.getUserId());
            ps.setDate(2, Date.valueOf(kyc.getDob()));
            ps.setString(3, kyc.getGender().name());
            ps.setString(4, kyc.getCitizenship());
            ps.setDate(5, Date.valueOf(kyc.getIssue()));
            ps.setString(6, kyc.getDistrict());
            ps.setString(7, kyc.getPhone());
            ps.setString(8, kyc.getOccupation());
            ps.setDouble(9, kyc.getIncome());
            ps.setString(10, kyc.getStatus().name().toUpperCase());
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to save the user {e} ", e);
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Kyc findById(Long id, Connection con) {
        return null;
    }

    @Override
    public boolean existsByCitizenship(String citizenshipNumber, Connection con) {
        Kyc kyc;
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_KYC_QUERY);
        ) {
            ps.setString(1, citizenshipNumber);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retrieve the kyc {e} ", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean existsByUserId(Long userId, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_KYC_BY_USERID);
        ) {
            ps.setLong(1, userId);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return false;
    }

    public Optional<Kyc> findByUserId(Long userId, Connection con) {
        Kyc kyc;
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_KYC_BY_USERID);

        ) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean verifyKyc(Long kycId, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.VERIFY_KYC_QUERY);
        ) {
            ps.setLong(1, kycId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return false;
    }
}
