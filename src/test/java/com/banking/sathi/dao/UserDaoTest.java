package com.banking.sathi.dao;

import com.banking.sathi.enums.Role;
import com.banking.sathi.enums.UserStatus;
import com.banking.sathi.model.User;
import com.banking.sathi.service.AuthService;
import com.banking.sathi.utils.DbConnection;

import java.sql.SQLException;

public class UserDaoTest {


    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao(DbConnection.getConnection());
        AuthService authService = new AuthService();
        User user = new User(
                "Dikshya Acharya",
                "dikshya04@gmail.com",
                "Root@123456789",
                "California",
                Role.USER,
                UserStatus.ACTIVE
        );
        try {
            boolean b = authService.registerUser(user);
            System.out.println(b);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
