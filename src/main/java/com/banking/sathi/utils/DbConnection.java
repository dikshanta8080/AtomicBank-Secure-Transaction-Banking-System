package com.banking.sathi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private static final Logger logger = Logger.getLogger(DbConnection.class.getName());
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/atomicbank_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            logger.log(Level.INFO, "Driver class loaded successfully");

        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Failed to load the Driver class, {e}", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
}
