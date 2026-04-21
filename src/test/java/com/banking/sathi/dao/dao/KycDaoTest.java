package com.banking.sathi.dao.dao;

import com.banking.sathi.dao.KycDao;
import com.banking.sathi.enums.Gender;
import com.banking.sathi.enums.KycStatus;
import com.banking.sathi.model.Kyc;
import com.banking.sathi.utils.DbConnection;

import java.sql.SQLException;
import java.time.LocalDate;

public class KycDaoTest {
    public static void main(String[] args) throws SQLException {
        KycDao kycDao = new KycDao();

        Kyc kyc = new Kyc(
                2L,
                LocalDate.of(1998, 3, 22),
                Gender.FEMALE,
                "9876543210",
                LocalDate.of(2016, 8, 5),
                "Lalitpur",
                "9800000000",
                "Software Engineer",
                80000.0,
                KycStatus.PENDING,
                null
        );
        int rowsInserted = kycDao.saveKyc(kyc, DbConnection.getConnection());
        System.out.println(rowsInserted);


    }
}
