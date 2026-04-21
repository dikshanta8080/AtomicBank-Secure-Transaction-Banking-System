package com.banking.sathi.dao;

import com.banking.sathi.model.Address;
import com.banking.sathi.repository.AddressRepository;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressDao implements AddressRepository {
    private static final Logger logger = Logger.getLogger(AddressDao.class.getName());

    @Override
    public int saveAddress(Address address, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_ADDRESS_QUERY);
        ) {
            ps.setLong(1, address.getUserId());
            ps.setString(2, address.getProvince());
            ps.setString(3, address.getDistrict());
            ps.setString(4, address.getCity());
            ps.setInt(5, address.getWard());
            ps.setString(6, address.getTole());
            return ps.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to execute the query {e}", e);
        }
        return 0;
    }

    @Override
    public boolean findById(Long id, Connection con) {
        return false;
    }
}
