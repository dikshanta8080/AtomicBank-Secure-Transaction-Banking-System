package com.banking.sathi.service;

import com.banking.sathi.dao.UserDao;
import com.banking.sathi.model.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserService {
    UserDao userDao = new UserDao();

    public Optional<User> getUserProfile(Long id, Connection con) {
        return userDao.findById(id, con);
    }

    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    public boolean deleteUser(Long id) {
        return userDao.deleteUserById(id) > 0;
    }

}
