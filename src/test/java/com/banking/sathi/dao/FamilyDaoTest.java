package com.banking.sathi.dao;

import com.banking.sathi.model.Family;
import com.banking.sathi.utils.DbConnection;

import java.sql.SQLException;

public class FamilyDaoTest {


    public static void main(String[] args) throws SQLException {
        FamilyDao familyDao = new FamilyDao();
        Family family = new Family(1L, "Pratik", "Sumit");
        int saveFamily = familyDao.saveFamily(family, DbConnection.getConnection());
        System.out.println(saveFamily);

    }

}
