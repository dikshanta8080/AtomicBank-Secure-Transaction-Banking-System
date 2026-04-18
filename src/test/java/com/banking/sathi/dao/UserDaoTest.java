package com.banking.sathi.dao;

import com.banking.sathi.model.User;
import com.banking.sathi.service.AuthService;
import com.banking.sathi.service.UserService;

import java.sql.SQLException;

public class UserDaoTest {


    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();
        AuthService authService = new AuthService();

//        User user = new User(
//                "Adarsh Acharya",
//                "adarsh04@gmail.com",
//                "Root@123456789",
//                Role.USER,
//                UserStatus.ACTIVE
//        );
//        try {
//            boolean b = authService.registerUser(user);
//            System.out.println(b);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        User userProfile = userService.getUserProfile(11L);
        System.out.println(userProfile);
    }
}
