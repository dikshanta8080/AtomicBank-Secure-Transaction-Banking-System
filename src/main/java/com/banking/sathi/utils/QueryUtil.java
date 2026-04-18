package com.banking.sathi.utils;

public class QueryUtil {
    public static final String INSERT_USER_QUERY = "INSERT INTO users (name,email,password,role,user_status) VALUES (?,?,?,?,?)";
    public static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
    public static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    public static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    public static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id=?";

}
