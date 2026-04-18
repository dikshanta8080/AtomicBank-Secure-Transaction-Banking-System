package com.banking.sathi.dao;

import com.banking.sathi.model.User;
import com.banking.sathi.repository.UserRepository;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements UserRepository {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());
    private final Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    @Override
    public int saveUser(User user) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_USER_QUERY);
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getRole().name());
            ps.setString(6, user.getUserStatus().name());
            return ps.executeUpdate();


        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to save the user {e} ", e);
        }
        return 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.EXISTS_BY_EMAIL_QUERY);

        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to execute the query {e}, ", e);

        }
        return false;
    }
}
