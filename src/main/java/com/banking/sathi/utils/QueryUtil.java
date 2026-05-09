package com.banking.sathi.utils;

public class QueryUtil {

    // ===================== USER =====================
    public static final String INSERT_USER_QUERY = "INSERT INTO users (name,email,password,role,user_status) VALUES (?,?,?,?,?)";
    public static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
    public static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    public static final String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    public static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id=?";
    public static final String FIND_USER_BY_ROLE = "SELECT * FROM users where role='ADMIN'";

    // ===================== FAMILY =====================
    public static final String INSERT_FAMILY_QUERY = "INSERT INTO family (user_id,father,mother) VALUES (?,?,?)";
    public static final String SELECT_FAMILY_BY_USERID = "SELECT * FROM family WHERE user_id=?";
    public static final String DELETE_FAMILY_BY_USERID = "DELETE FROM family WHERE user_id=?";

    // ===================== ADDRESS =====================
    public static final String INSERT_ADDRESS_QUERY = "INSERT INTO addresses (user_id,province,district,city,ward,tole) VALUES (?,?,?,?,?,?)";
    public static final String SELECT_ADDRESS_BY_USERID = "SELECT * FROM addresses WHERE user_id=?";
    public static final String DELETE_ADDRESS_BY_USERID = "DELETE FROM addresses WHERE user_id=?";

    // ===================== KYC =====================
    public static final String INSERT_KYC_QUERY = "INSERT INTO kyc (user_id,dob,gender,citizenship,issue_date,district,phone,occupation,income,status) VALUES (?,?,?,?,?,?,?,?,?,?)";
    public static final String SELECT_KYC_QUERY = "SELECT * FROM kyc WHERE id=?";
    public static final String SELECT_KYC_BY_USERID = "SELECT * FROM kyc WHERE user_id=?";
    public static final String VERIFY_KYC_QUERY = "UPDATE kyc SET status='VERIFIED' WHERE id=? ";
    public static final String DELETE_KYC_BY_USERID = "DELETE FROM kyc WHERE user_id=?";

    // ===================== ACCOUNT =====================
    public static final String INSERT_ACCOUNT_QUERY = "INSERT INTO accounts (user_id,account_number,transaction_pin,account_type,balance,account_status) VALUES (?,?,?,?,?,?)";
    public static final String DELETE_ACCOUNT_QUERY = "DELETE FROM accounts WHERE id=?";
    public static final String DELETE_ACCOUNT_BY_USERID = "DELETE FROM accounts WHERE user_id=?";
    public static final String FREEZE_ACCOUNT = "UPDATE accounts SET account_status='FROZEN' WHERE user_id=? ";
    public static final String VERIFY_ACCOUNT_QUERY = "UPDATE accounts SET account_status='ACTIVE' WHERE id=? ";
    public static final String FIND_BY_USERID_QUERY = "SELECT * FROM accounts WHERE user_id=? ";
    public static final String LOCK_ACCOUNT_ROW_FOR_UPDATE = "SELECT id, balance, account_status, transaction_pin " +
            "FROM accounts " +
            "WHERE id = ? " +
            "FOR UPDATE";
    public static final String DEPOSIT_MONEY = "UPDATE accounts SET balance= balance + ? WHERE id=?";
    public static final String WITHDRAW_MONEY = "UPDATE accounts SET balance= balance - ? WHERE id=?";
    public static final String GET_TOTAL_ACCOUNT_COUNT = "SELECT COUNT(*) as totalAccounts FROM accounts";
    public static final String GET_TOTAL_DEPOSITS = "SELECT SUM(a.balance) as deposits FROM accounts a";
    public static final String FIND_BALANCE_BY_USERID = "SELECT balance FROM accounts WHERE user_id=?";
    public static final String GET_NUMBER_OF_PENDING_ACCOUNTS = "SELECT count(*) AS pendingApprovals FROM accounts a WHERE a.account_status='INACTIVE'";
    public static final String SELECT_PENDING_ACCOUNT_APPROVALS = "SELECT \n" +
            "    u.id AS userId,\n" +
            "    u.name AS name,\n" +
            "    u.email AS email,\n" +
            "    k.phone AS phone,\n" +
            "    k.status AS kycStatus,\n" +
            "    a.account_type AS accountType,\n" +
            "    a.account_status AS accountStatus\n" +
            "FROM users u\n" +
            "LEFT JOIN kyc k ON u.id = k.user_id\n" +
            "LEFT JOIN accounts a ON u.id = a.user_id\n" +
            "WHERE k.status = 'PENDING';";
    public static final String SELECT_PENDING_ACCOUNT_DETAILS = "SELECT \n" +
            "    u.id AS userId,\n" +
            "    u.name AS name,\n" +
            "    u.email AS email,\n" +
            "\n" +
            "    k.dob AS dob,\n" +
            "    k.gender AS gender,\n" +
            "    k.citizenship AS citizenship,\n" +
            "    k.issue_date AS citizenshipIssueDate,\n" +
            "    k.district AS citizenshipDistrict,\n" +
            "    k.phone AS phone,\n" +
            "    k.occupation AS occupation,\n" +
            "    k.income AS income,\n" +
            "    k.status AS kycStatus,\n" +
            "\n" +
            "    a.account_number AS accountNumber,\n" +
            "    a.account_type AS accountType,\n" +
            "    a.account_status AS accountStatus,\n" +
            "\n" +
            "    ad.province AS province,\n" +
            "    ad.district AS district,\n" +
            "    ad.city AS city,\n" +
            "    ad.ward AS ward,\n" +
            "    ad.tole AS tole,\n" +
            "\n" +
            "    f.father AS fatherName,\n" +
            "    f.mother AS motherName\n" +
            "\n" +
            "FROM users u\n" +
            "LEFT JOIN kyc k ON u.id = k.user_id\n" +
            "LEFT JOIN accounts a ON u.id = a.user_id\n" +
            "LEFT JOIN addresses ad ON u.id = ad.user_id\n" +
            "LEFT JOIN family f ON u.id = f.user_id\n" +
            "WHERE u.id = ?;";

    // ===================== CARD =====================
    public static final String INSERT_CARD_QUERY = "INSERT INTO CARD (account_id,card_number,type,status,credit_limit,expiry_date,cvv) VALUES (?,?,?,?,?,?,?)";
    public static final String DELETE_CARD_QUERY = "DELETE FROM cards WHERE id=?";
    public static final String FIND_CARD_BY_ID = "SELECT * FROM cards WHERE id=?";
    public static final String FIND_CARD_BY_ACCOUNT = "SELECT * FROM cards WHERE account_id=?";
    public static final String FIND_ALL_CARDS = "SELECT * FROM cards";
    public static final String FIND_PENDING_APPROVAL_CARDS = "SELECT * FROM cards WHERE status='PENDING'";
    public static final String VERIFY_CARD = "UPDATE cards SET status='APPROVED' WHERE id=?";
    public static final String REJECT_CARD = "UPDATE cards SET status='REJECTED' WHERE id=?";
    public static final String FIND_NUMBER_OF_PENDING_CARD_APPROVALS = "SELECT COUNT(*) as numberOfPendingCard FROM cards WHERE status='PENDING'";
    public static final String FIND_TOTAL_NUMBER_OF_CARDS = "SELECT COUNT(*) as totalCards FROM cards ";
    public static final String FIND_KYC_INCOME_BY_ACCOUNT = "SELECT income as annualAincome FROM KYC k\n" +
            "JOIN users u \n" +
            "ON k.user_id=u.id\n" +
            "JOIN accounts a\n" +
            "ON a.user_id=u.id\n" +
            "WHERE a.id=?;";

    // ===================== TRANSACTION =====================
    public static final String INSERT_TRANSACTION_QUERY = "INSERT INTO transactions (from_account_id, to_account_id, type, status, amount, remarks) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String FIND_BALANCE_BY_ACCOUNT_ID = "SELECT balance FROM accounts WHERE id=?";

}