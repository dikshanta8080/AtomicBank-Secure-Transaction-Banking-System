package com.banking.sathi.dao;

import com.banking.sathi.enums.Role;
import com.banking.sathi.enums.UserStatus;
import com.banking.sathi.model.User;
import com.banking.sathi.service.AuthService;
import com.banking.sathi.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {


    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();
        AuthService authService = new AuthService();

        User user = new User(
                "Adarsh Acharya",
                "adarsh04@gmail.com",
                "Root@123456789",
                Role.USER,
                UserStatus.ACTIVE
        );
        try {
            boolean b = authService.registerUser(user);
            System.out.println(b);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<User> users = userService.getAllUsers();
        users.forEach(System.out::println);
    }
}
