package com.banking.sathi.utils;

public interface QueryUtil {

    // ===================== USER =====================
    String INSERT_USER_QUERY = "INSERT INTO users (name,email,password,role,user_status) VALUES (?,?,?,?,?)";
    String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email=?";
    String FIND_USER_BY_ID_QUERY = "SELECT * FROM users WHERE id=?";
    String FIND_ALL_USERS_QUERY = "SELECT * FROM users";
    String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id=?";
    String FIND_USER_BY_ROLE = "SELECT * FROM users where role='ADMIN'";

    // ===================== FAMILY =====================
    String INSERT_FAMILY_QUERY = "INSERT INTO family (user_id,father,mother) VALUES (?,?,?)";
    String SELECT_FAMILY_BY_USERID = "SELECT * FROM family WHERE user_id=?";
    String DELETE_FAMILY_BY_USERID = "DELETE FROM family WHERE user_id=?";

    // ===================== ADDRESS =====================
    String INSERT_ADDRESS_QUERY = "INSERT INTO addresses (user_id,province,district,city,ward,tole) VALUES (?,?,?,?,?,?)";
    String SELECT_ADDRESS_BY_USERID = "SELECT * FROM addresses WHERE user_id=?";
    String DELETE_ADDRESS_BY_USERID = "DELETE FROM addresses WHERE user_id=?";

    // ===================== KYC =====================
    String INSERT_KYC_QUERY = "INSERT INTO kyc (user_id,dob,gender,citizenship,issue_date,district,phone,occupation,income,status) VALUES (?,?,?,?,?,?,?,?,?,?)";
    String SELECT_KYC_QUERY = "SELECT * FROM kyc WHERE id=?";
    String SELECT_KYC_BY_USERID = "SELECT * FROM kyc WHERE user_id=?";
    String VERIFY_KYC_QUERY = "UPDATE kyc SET status='VERIFIED' WHERE id=?";
    String DELETE_KYC_BY_USERID = "DELETE FROM kyc WHERE user_id=?";

    // ===================== ACCOUNT =====================
    String INSERT_ACCOUNT_QUERY = "INSERT INTO accounts (user_id,account_number,transaction_pin,account_type,balance,account_status) VALUES (?,?,?,?,?,?)";
    String DELETE_ACCOUNT_QUERY = "DELETE FROM accounts WHERE id=?";
    String DELETE_ACCOUNT_BY_USERID = "DELETE FROM accounts WHERE user_id=?";
    String FREEZE_ACCOUNT = "UPDATE accounts SET account_status='FROZEN' WHERE user_id=?";
    String VERIFY_ACCOUNT_QUERY = "UPDATE accounts SET account_status='ACTIVE' WHERE id=?";
    String FIND_BY_USERID_QUERY = "SELECT * FROM accounts WHERE user_id=?";

    String LOCK_ACCOUNT_ROW_FOR_UPDATE =
            "SELECT id, balance, account_status, transaction_pin FROM accounts WHERE id = ? FOR UPDATE";

    String DEPOSIT_MONEY = "UPDATE accounts SET balance = balance + ? WHERE id=?";
    String WITHDRAW_MONEY = "UPDATE accounts SET balance = balance - ? WHERE id=?";
    String GET_TOTAL_ACCOUNT_COUNT = "SELECT COUNT(*) as totalAccounts FROM accounts";
    String GET_TOTAL_DEPOSITS = "SELECT SUM(a.balance) as deposits FROM accounts a";
    String FIND_BALANCE_BY_USERID = "SELECT balance FROM accounts WHERE user_id=?";
    String GET_NUMBER_OF_PENDING_ACCOUNTS =
            "SELECT count(*) AS pendingApprovals FROM accounts a WHERE a.account_status='INACTIVE'";

    String SELECT_PENDING_ACCOUNT_APPROVALS =
            "SELECT u.id AS userId, u.name AS name, u.email AS email, k.phone AS phone, " +
                    "k.status AS kycStatus, a.account_type AS accountType, a.account_status AS accountStatus " +
                    "FROM users u " +
                    "LEFT JOIN kyc k ON u.id = k.user_id " +
                    "LEFT JOIN accounts a ON u.id = a.user_id " +
                    "WHERE k.status = 'PENDING'";

    String SELECT_PENDING_ACCOUNT_DETAILS =
            "SELECT u.id AS userId, u.name AS name, u.email AS email, " +
                    "k.dob AS dob, k.gender AS gender, k.citizenship AS citizenship, " +
                    "k.issue_date AS citizenshipIssueDate, k.district AS citizenshipDistrict, " +
                    "k.phone AS phone, k.occupation AS occupation, k.income AS income, k.status AS kycStatus, " +
                    "a.account_number AS accountNumber, a.account_type AS accountType, a.account_status AS accountStatus, " +
                    "ad.province AS province, ad.district AS district, ad.city AS city, ad.ward AS ward, ad.tole AS tole, " +
                    "f.father AS fatherName, f.mother AS motherName " +
                    "FROM users u " +
                    "LEFT JOIN kyc k ON u.id = k.user_id " +
                    "LEFT JOIN accounts a ON u.id = a.user_id " +
                    "LEFT JOIN addresses ad ON u.id = ad.user_id " +
                    "LEFT JOIN family f ON u.id = f.user_id " +
                    "WHERE u.id = ?";

    String SELECT_ALL_ACCOUNTS_FOR_ADMIN =
            "SELECT u.id AS user_id, u.name, u.email, a.account_number, a.account_type, a.account_status, a.balance " +
                    "FROM users u JOIN accounts a ON a.user_id = u.id ORDER BY a.created DESC";

    String SELECT_TRANSFER_TARGETS =
            "SELECT u.id AS user_id, a.id AS account_id, u.name, a.account_number " +
                    "FROM users u JOIN accounts a ON a.user_id = u.id " +
                    "WHERE u.id <> ? AND a.account_status = 'ACTIVE' ORDER BY u.name";

    // ===================== CARD =====================
    String INSERT_CARD_QUERY =
            "INSERT INTO cards (account_id,card_number,type,status,credit_limit,expiry_date,cvv) VALUES (?,?,?,?,?,?,?)";
    String DELETE_CARD_QUERY = "DELETE FROM cards WHERE id=?";
    String FIND_CARD_BY_ID = "SELECT * FROM cards WHERE id=?";
    String FIND_CARD_BY_ACCOUNT = "SELECT * FROM cards WHERE account_id=?";
    String FIND_ALL_CARDS = "SELECT * FROM cards";

    String FIND_PENDING_APPROVAL_CARDS =
            "SELECT c.id, c.account_id, c.card_number, c.type, c.status, c.credit_limit, c.expiry_date, c.created, " +
                    "u.name AS user_name, a.account_number AS account_number " +
                    "FROM cards c " +
                    "JOIN accounts a ON c.account_id = a.id " +
                    "JOIN users u ON a.user_id = u.id " +
                    "WHERE c.status = 'PENDING' ORDER BY c.created DESC";

    String VERIFY_CARD = "UPDATE cards SET status='APPROVED' WHERE id=?";
    String REJECT_CARD = "UPDATE cards SET status='REJECTED' WHERE id=?";
    String ISSUE_CARD = "UPDATE cards SET status='ISSUED', credit_limit=?, expiry_date=? WHERE id=?";
    String FIND_NUMBER_OF_PENDING_CARD_APPROVALS =
            "SELECT COUNT(*) as numberOfPendingCard FROM cards WHERE status='PENDING'";
    String FIND_TOTAL_NUMBER_OF_CARDS =
            "SELECT COUNT(*) as totalCards FROM cards";

    String FIND_KYC_INCOME_BY_ACCOUNT =
            "SELECT income AS annualAincome FROM kyc k " +
                    "JOIN users u ON k.user_id = u.id " +
                    "JOIN accounts a ON a.user_id = u.id " +
                    "WHERE a.id = ?";

    String COUNT_CARDS_BY_STATUS_FOR_USER =
            "SELECT COUNT(*) AS total FROM cards WHERE account_id = ? AND status = ?";

    // ===================== TRANSACTION =====================
    String INSERT_TRANSACTION_QUERY =
            "INSERT INTO transactions (from_account_id, to_account_id, type, status, amount, remarks) VALUES (?, ?, ?, ?, ?, ?)";

    String FIND_BALANCE_BY_ACCOUNT_ID =
            "SELECT balance FROM accounts WHERE id=?";

    String SELECT_MONTHLY_INCOME_BY_USER =
            "SELECT income FROM kyc WHERE user_id = ?";

    String TRANSACTION_BASE_SELECT =
            "SELECT t.id, t.from_account_id, t.to_account_id, t.type, t.status, t.amount, t.remarks, t.created, " +
                    "fa.account_number AS from_account_number, ta.account_number AS to_account_number, " +
                    "fu.name AS from_user_name, tu.name AS to_user_name " +
                    "FROM transactions t " +
                    "LEFT JOIN accounts fa ON fa.id = t.from_account_id " +
                    "LEFT JOIN accounts ta ON ta.id = t.to_account_id " +
                    "LEFT JOIN users fu ON fu.id = fa.user_id " +
                    "LEFT JOIN users tu ON tu.id = ta.user_id ";

    String SELECT_TRANSACTIONS_BY_USER =
            TRANSACTION_BASE_SELECT + "WHERE (fa.user_id = ? OR ta.user_id = ?) ORDER BY t.created DESC";

    String SELECT_TRANSACTIONS_BY_USER_FROM_DATE =
            TRANSACTION_BASE_SELECT +
                    "WHERE (fa.user_id = ? OR ta.user_id = ?) AND DATE(t.created) >= ? ORDER BY t.created DESC";

    String SELECT_TRANSACTIONS_BY_USER_TO_DATE =
            TRANSACTION_BASE_SELECT +
                    "WHERE (fa.user_id = ? OR ta.user_id = ?) AND DATE(t.created) <= ? ORDER BY t.created DESC";

    String SELECT_TRANSACTIONS_BY_USER_DATE_RANGE =
            TRANSACTION_BASE_SELECT +
                    "WHERE (fa.user_id = ? OR ta.user_id = ?) AND DATE(t.created) >= ? AND DATE(t.created) <= ? ORDER BY t.created DESC";

    String SELECT_ALL_TRANSACTIONS =
            TRANSACTION_BASE_SELECT + "ORDER BY t.created DESC";

    String SELECT_FAILED_TRANSACTIONS =
            TRANSACTION_BASE_SELECT +
                    "WHERE t.status IN ('FAILED', 'ROLLED_BACK') ORDER BY t.created DESC";

    String COUNT_TRANSACTIONS_BY_STATUS =
            "SELECT COUNT(*) AS total FROM transactions WHERE status = ?";

    String COUNT_TRANSACTIONS_FOR_USER =
            "SELECT COUNT(*) AS total FROM transactions t " +
                    "LEFT JOIN accounts fa ON fa.id = t.from_account_id " +
                    "LEFT JOIN accounts ta ON ta.id = t.to_account_id " +
                    "WHERE fa.user_id = ? OR ta.user_id = ?";
}