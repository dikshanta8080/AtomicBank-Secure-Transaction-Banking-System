package com.banking.sathi.repository;

import com.banking.sathi.model.Kyc;

import java.sql.Connection;

public interface KycRepository {
    boolean saveKyc(Kyc kyc, Connection con);

    Kyc findById(Long id, Connection con);


}
