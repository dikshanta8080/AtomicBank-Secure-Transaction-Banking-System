package com.banking.sathi.dao;

import com.banking.sathi.model.Kyc;
import com.banking.sathi.repository.KycRepository;

import java.sql.Connection;

public class KycDao implements KycRepository {

    @Override
    public boolean saveKyc(Kyc kyc, Connection con) {
        return false;
    }

    @Override
    public Kyc findById(Long id, Connection con) {
        return null;
    }
}
