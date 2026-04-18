package com.banking.sathi.service;

import com.banking.sathi.dao.UserDao;
import com.banking.sathi.enums.Role;
import com.banking.sathi.enums.UserStatus;
import com.banking.sathi.exceptions.UserAlreadyExistsException;
import com.banking.sathi.model.User;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.validators.UserValidator;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthService {
    private static final Logger logger = Logger.getLogger(AuthService.class.getName());

    public boolean registerUser(User user) {
        Connection con = null;
        try {
            con = DbConnection.getConnection();
            con.setAutoCommit(false);
            UserDao userDao = new UserDao(con);
            UserValidator.validateCredentialsForRegistration(user);

            if (userDao.existsByEmail(user.getEmail())) {
                throw new UserAlreadyExistsException("User with this email already exists");
            }
            String encodedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(11));
            user.setPassword(encodedPassword);
            user.setRole(Role.USER);
            user.setUserStatus(UserStatus.ACTIVE);
            int result = userDao.saveUser(user);
            con.commit();
            return result > 0;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, "Exception {ex}, ", ex);
                }

            }
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Exception {ex}, ", e);
                }
            }
        }
    }
}
