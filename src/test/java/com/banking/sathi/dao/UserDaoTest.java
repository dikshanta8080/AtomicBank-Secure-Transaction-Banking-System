package com.banking.sathi.dao;

import com.banking.sathi.enums.Role;
import com.banking.sathi.enums.UserStatus;
import com.banking.sathi.model.User;

public class UserDaoTest {


    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User user = new User(
                "Dikshanta Acharya",
                "dikshanta04@gmail.com",
                "root",
                "Itahari",
                Role.USER,
                UserStatus.ACTIVE
        );
        boolean isSaved = userDao.saveUser(user);
        System.out.println(isSaved);
    }
}
