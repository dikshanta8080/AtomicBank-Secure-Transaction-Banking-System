package com.banking.sathi.repository;

import com.banking.sathi.model.User;

public interface UserRepository {
    int saveUser(User user);

    boolean existsByEmail(String email);


}
