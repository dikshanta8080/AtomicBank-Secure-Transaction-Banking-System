package com.banking.sathi.service;

import com.banking.sathi.dao.UserDao;
import com.banking.sathi.model.User;

import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();

    public User getUserProfile(Long id) {
        return userDao.findById(id);
    }

    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    public boolean deleteUser(Long id) {
        return userDao.deleteUserById(id) > 0;
    }

}
