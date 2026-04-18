package com.banking.sathi.dao;

import com.banking.sathi.model.User;
import com.banking.sathi.repository.UserRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements UserRepository {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    @Override
    public boolean saveUser(User user) {
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_USER_QUERY);
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getRole().name());
            ps.setString(6, user.getUserStatus().name());
            int rowsEffected = ps.executeUpdate();
            return rowsEffected > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to save the user {e} ", e);
        }
        return false;
    }
}
