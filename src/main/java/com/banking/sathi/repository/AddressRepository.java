package com.banking.sathi.repository;

import com.banking.sathi.model.Address;

import java.sql.Connection;

public interface AddressRepository {
    int saveAddress(Address address, Connection con);

    boolean findById(Long id, Connection con);

    boolean existsByUserId(Long userId, Connection con);

    boolean deleteByUserId(Long userId, Connection con);

}
