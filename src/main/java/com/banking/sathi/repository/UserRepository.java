package com.banking.sathi.repository;

import com.banking.sathi.model.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    int saveUser(User user, Connection con);

    boolean existsByEmail(String email, Connection connection);

    Optional<User> findByEmail(String email);

    User findById(Long id);

    List<User> findAllUsers();

    int deleteUserById(Long id);


}
