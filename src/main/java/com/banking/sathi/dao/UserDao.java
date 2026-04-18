package com.banking.sathi.dao;

import com.banking.sathi.enums.Role;
import com.banking.sathi.enums.UserStatus;
import com.banking.sathi.model.User;
import com.banking.sathi.repository.UserRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements UserRepository {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    @Override
    public int saveUser(User user, Connection con) {
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
    public boolean existsByEmail(String email, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_USER_BY_EMAIL_QUERY);

        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to execute the query {e}, ", e);

        }
        return false;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_USER_BY_EMAIL_QUERY);

        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            user = new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("address"),
                    Role.valueOf(rs.getString("role")),
                    UserStatus.valueOf(rs.getString("user_status"))
            );
            return Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to execute the query {e}, ", e);
        }
        return Optional.empty();
    }
}
