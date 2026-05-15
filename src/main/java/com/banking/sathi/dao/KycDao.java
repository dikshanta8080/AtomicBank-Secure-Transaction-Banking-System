package com.banking.sathi.dao;

import com.banking.sathi.enums.Gender;
import com.banking.sathi.model.Kyc;
import com.banking.sathi.repository.KycRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.*;
import java.time.LocalDate;
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
            logger.log(Level.SEVERE, "Failed to save kyc", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Kyc findById(Long id, Connection con) {
        return null;
    }

    @Override
    public boolean existsByCitizenship(String citizenshipNumber, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement("SELECT 1 FROM kyc WHERE citizenship=?");
        ) {
            ps.setString(1, citizenshipNumber);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to check citizenship", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByUserId(Long userId, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_KYC_BY_USERID);
        ) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to check kyc by user id", e);
            throw new RuntimeException(e);
        }
    }

    public Optional<Kyc> findByUserId(Long userId, Connection con) {

        try (PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_KYC_BY_USERID)) {

            ps.setLong(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Kyc kyc = new Kyc();

                    kyc.setId(rs.getLong("id"));
                    kyc.setUserId(rs.getLong("user_id"));
                    kyc.setDob(rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null);
                    kyc.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
                    kyc.setIncome(rs.getDouble("income"));
                    kyc.setIssue(rs.getObject("issue_date", LocalDate.class));
                    kyc.setCitizenship(rs.getString("citizenship"));
                    kyc.setDistrict(rs.getString("district"));
                    kyc.setPhone(rs.getString("phone"));
                    kyc.setOccupation(rs.getString("occupation"));
                    kyc.setReason(rs.getString("reason"));


                    return Optional.of(kyc);
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query", e);
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteByUserId(Long userId, Connection con) {

        try (PreparedStatement ps = con.prepareStatement(QueryUtil.DELETE_KYC_BY_USERID)) {
            ps.setLong(1, userId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);

        }
        return false;
    }

    @Override
    public Double findKycIncomeByAccount(Long accountId) {
        Double annualIncome = 0.0d;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_KYC_INCOME_BY_ACCOUNT);
        ) {
            ps.setLong(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) annualIncome = rs.getDouble("annualAincome");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to retreve the income");
        }
        return annualIncome;
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
