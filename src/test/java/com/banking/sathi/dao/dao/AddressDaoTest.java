package com.banking.sathi.dao.dao;

import com.banking.sathi.dao.AddressDao;
import com.banking.sathi.model.Address;
import com.banking.sathi.utils.DbConnection;

import java.sql.SQLException;

public class AddressDaoTest {
    public static void main(String[] args) throws SQLException {
        AddressDao addressDao = new AddressDao();
        Address address = new Address(
                2L,
                "Bagmati",
                "Kathmandu",
                "Kathmandu",
                5,
                "Baneshwor"

        );
        int rowsInserted = addressDao.saveAddress(address, DbConnection.getConnection());
        System.out.println(rowsInserted);
    }
}
