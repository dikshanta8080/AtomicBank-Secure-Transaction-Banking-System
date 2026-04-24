package com.banking.sathi.utils;

public class QueryUtil {

    // ===================== USER =====================
    public static final String INSERT_USER_QUERY = "INSERT INTO users (name,email,password,role,user_status) VALUES (?,?,?,?,?)";
    public static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
    public static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    public static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    public static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id=?";

    // ===================== FAMILY =====================
    public static final String INSERT_FAMILY_QUERY = "INSERT INTO family (user_id,father,mother) VALUES (?,?,?)";
    public static final String SELECT_FAMILY_BY_USERID = "SELECT * FROM family WHERE user_id=?";

    // ===================== ADDRESS =====================
    public static final String INSERT_ADDRESS_QUERY = "INSERT INTO addresses (user_id,province,district,city,ward,tole) VALUES (?,?,?,?,?,?)";
    public static final String SELECT_ADDRESS_BY_USERID = "SELECT * FROM addresses WHERE user_id=?";

    // ===================== KYC =====================
    public static final String INSERT_KYC_QUERY = "INSERT INTO kyc (user_id,dob,gender,citizenship,issue_date,district,phone,occupation,income,status) VALUES (?,?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_KYC_QUERY = "SELECT * FROM kyc WHERE id=?";
    public static final String SELECT_KYC_BY_USERID = "SELECT * FROM kyc WHERE user_id=?";

    // ===================== ACCOUNT =====================
    public static final String INSERT_ACCOUNT_QUERY = "INSERT INTO accounts (user_id,account_number,transaction_pin,account_type,balance,account_status) VALUES (?,?,?,?,?,?)";

}