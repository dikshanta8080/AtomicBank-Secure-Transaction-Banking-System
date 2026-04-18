package com.banking.sathi.utils;

public class QueryUtil {
    public static final String INSERT_USER_QUERY = "INSERT INTO users (name,email,password,address,role,user_status) VALUES (?,?,?,?,?,?)";
    public static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
}
