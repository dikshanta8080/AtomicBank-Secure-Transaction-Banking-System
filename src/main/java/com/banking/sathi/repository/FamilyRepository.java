package com.banking.sathi.repository;

import com.banking.sathi.model.Family;

import java.sql.Connection;

public interface FamilyRepository {
    int saveFamily(Family family, Connection con);

    Family findById(Long id, Connection con);

    boolean existsByUserId(Long userId, Connection con);

    boolean deleteByUserId(Long userId, Connection con);

}
