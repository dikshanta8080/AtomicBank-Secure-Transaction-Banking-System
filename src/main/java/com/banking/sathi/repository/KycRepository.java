package com.banking.sathi.repository;

import com.banking.sathi.model.Kyc;

import java.sql.Connection;
import java.util.Optional;

public interface KycRepository {
    int saveKyc(Kyc kyc, Connection con);

    Kyc findById(Long id, Connection con);

    boolean existsByCitizenship(String citizenshipNumber, Connection con);

    boolean existsByUserId(Long userId, Connection con);

    boolean verifyKyc(Long kycId, Connection con);

    Optional<Kyc> findByUserId(Long userId, Connection con);

    boolean deleteByUserId(Long userId, Connection con);


}
