package com.banking.sathi.repository;

import com.banking.sathi.model.Kyc;

import java.sql.Connection;

public interface KycRepository {
    int saveKyc(Kyc kyc, Connection con);

    Kyc findById(Long id, Connection con);

    boolean existsByCitizenship(String citizenshipNumber, Connection con);

    boolean existsByUserId(Long userId, Connection con);


}
