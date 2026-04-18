package com.banking.sathi.service;

import com.banking.sathi.dao.UserDao;
import com.banking.sathi.model.User;

public class UserService {
    UserDao userDao = new UserDao();

    public User getUserProfile(Long id) {
        return userDao.findById(id);
    }

}
