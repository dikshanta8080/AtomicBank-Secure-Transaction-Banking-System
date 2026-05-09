package com.banking.sathi.dao;

import com.banking.sathi.dto.response.AccountDetailDTO;
import com.banking.sathi.dto.response.AccountListDTO;
import com.banking.sathi.enums.AccountStatus;
import com.banking.sathi.enums.AccountType;
import com.banking.sathi.enums.KycStatus;
import com.banking.sathi.model.Account;
import com.banking.sathi.repository.AccountRepository;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDao implements AccountRepository {
    private static final Logger logger = Logger.getLogger(AccountDao.class.getName());

    @Override
    public int saveAccount(Account account, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.INSERT_ACCOUNT_QUERY);

        ) {
            ps.setLong(1, account.getUserId());
            ps.setString(2, account.getAccountNumber());
            ps.setString(3, account.getTransactionPin());
            ps.setString(4, account.getType().name());
            ps.setDouble(5, account.getBalance());
            ps.setString(6, account.getStatus().name());
            return ps.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to execute the query {e}", e);
        }
        return 0;
    }

    @Override
    public Account findById(Long id, Connection con) {
        return null;
    }

    @Override
    public boolean existsByUserId(Long userId, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_FAMILY_BY_USERID);
        ) {
            ps.setLong(1, userId);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return false;
    }

    @Override
    public int deleteAccountById(Long accountId) {
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.DELETE_ACCOUNT_QUERY);
        ) {
            ps.setLong(1, accountId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return 0;
    }

    @Override
    public boolean verifyAccount(Long accountId, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.VERIFY_ACCOUNT_QUERY);
        ) {
            ps.setLong(1, accountId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return false;
    }


    @Override
    public Optional<Account> findByUserId(Long userId, Connection con) {
        Account account = null;
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_BY_USERID_QUERY);
        ) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setId(rs.getLong("id"));
                account.setUserId(rs.getLong("user_id"));
                account.setType(AccountType.valueOf(rs.getString("account_type").toUpperCase()));
                account.setStatus(AccountStatus.valueOf(rs.getString("account_status").toUpperCase()));
                account.setAccountNumber(rs.getString("account_number"));
                account.setTransactionPin(rs.getString("transaction_pin"));
                account.setBalance(rs.getDouble("balance"));
            }
            return Optional.of(account);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> findByUserId(Long userId) {
        Account account = null;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_BY_USERID_QUERY);
        ) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setId(rs.getLong("id"));
                account.setUserId(rs.getLong("user_id"));
                account.setType(AccountType.valueOf(rs.getString("account_type").toUpperCase()));
                account.setStatus(AccountStatus.valueOf(rs.getString("account_status").toUpperCase()));
                account.setAccountNumber(rs.getString("account_number"));
                account.setTransactionPin(rs.getString("transaction_pin"));
                account.setBalance(rs.getDouble("balance"));
            }
            return Optional.ofNullable(account);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean freezeAccount(Long userId) {
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FREEZE_ACCOUNT);
        ) {
            ps.setLong(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return false;
    }

    @Override
    public int getNumberOfPendingApprovals() {
        int approvals = 0;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.GET_NUMBER_OF_PENDING_ACCOUNTS);
                ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) approvals = rs.getInt("pendingApprovals");

            return approvals;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to get number of pending approvals {e}", e);
        }
        return approvals;
    }

    @Override
    public int getTotalNumberOfAccounts() {
        int accounts = 0;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.GET_TOTAL_ACCOUNT_COUNT);
                ResultSet rs = ps.executeQuery();

        ) {

            if (rs.next()) accounts = rs.getInt("totalAccounts");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return accounts;
    }

    @Override
    public double getTotalDeposits() {
        double deposits = 0.0d;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.GET_TOTAL_DEPOSITS);
                ResultSet rs = ps.executeQuery();
        ) {

            if (rs.next()) deposits = rs.getDouble("deposits");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to get the total number of deposits {e}", e);
        }
        return deposits;
    }

    @Override
    public double getBalanceByUserId(Long userId) {
        double balance = 0.0d;
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.FIND_BALANCE_BY_USERID);
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) balance = rs.getDouble("balance");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to get the total number of deposits {e}", e);
        }
        return balance;
    }

    @Override
    public Account findAccountByUserId(Long userId) {
        return null;
    }

    @Override
    public int deposit(Long accountId, Double balance, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.DEPOSIT_MONEY);
        ) {
            ps.setDouble(1, balance);
            ps.setLong(2, accountId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to deposit!");
        }
        return 0;
    }

    @Override
    public int withdraw(Long accountId, Double balance, Connection con) {
        try (
                PreparedStatement ps = con.prepareStatement(QueryUtil.WITHDRAW_MONEY);
        ) {
            ps.setDouble(1, balance);
            ps.setLong(2, accountId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to deposit!");
        }
        return 0;
    }

    @Override
    public Account lockRowsForUpdate(Connection con, Long accountId) {

        Account account = null;

        try (PreparedStatement ps =
                     con.prepareStatement(QueryUtil.LOCK_ACCOUNT_ROW_FOR_UPDATE)) {

            ps.setLong(1, accountId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    account = new Account();

                    account.setId(rs.getLong("id"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setStatus(AccountStatus.valueOf(rs.getString("account_status")));
                    account.setTransactionPin(rs.getString("transaction_pin"));
                } else {
                    throw new RuntimeException("Account not found");
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE,
                    "Failed to lock account row for update", e);
            throw new RuntimeException(e);
        }

        return account;
    }

    @Override
    public List<AccountListDTO> getPendingAccounts() {

        List<AccountListDTO> list = new ArrayList<>();

        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_PENDING_ACCOUNT_APPROVALS);
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                AccountListDTO dto = new AccountListDTO();

                dto.setUserId(rs.getLong("userId"));
                dto.setName(rs.getString("name"));
                dto.setEmail(rs.getString("email"));
                dto.setPhone(rs.getString("phone"));

                String kycStatusStr = rs.getString("kycStatus");
                if (kycStatusStr != null) {
                    dto.setKycStatus(KycStatus.valueOf(kycStatusStr.toUpperCase()));
                }

                String accountTypeStr = rs.getString("accountType");
                if (accountTypeStr != null) {
                    dto.setAccountType(AccountType.valueOf(accountTypeStr.toUpperCase()));
                }

                String accountStatusStr = rs.getString("accountStatus");
                if (accountStatusStr != null) {
                    dto.setAccountStatus(AccountStatus.valueOf(accountStatusStr.toUpperCase()));
                }

                list.add(dto);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query", e);
            throw new RuntimeException("Failed to fetch pending accounts", e);
        }

        return list;
    }

    @Override
    public AccountDetailDTO getDetailedPendingApproval(Long userId) {

        AccountDetailDTO dto = null;

        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_PENDING_ACCOUNT_DETAILS);
        ) {

            ps.setLong(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                dto = new AccountDetailDTO();

                dto.setUserId(rs.getLong("userId"));
                dto.setName(rs.getString("name"));
                dto.setEmail(rs.getString("email"));

                dto.setDob(rs.getString("dob"));
                dto.setGender(rs.getString("gender"));
                dto.setCitizenship(rs.getString("citizenship"));
                dto.setPhone(rs.getString("phone"));
                dto.setOccupation(rs.getString("occupation"));

                String kycStatus = rs.getString("kycStatus");
                if (kycStatus != null) {
                    dto.setKycStatus(KycStatus.valueOf(kycStatus.toUpperCase()));
                }

                dto.setAccountNumber(rs.getString("accountNumber"));

                String accountType = rs.getString("accountType");
                if (accountType != null) {
                    dto.setAccountType(AccountType.valueOf(accountType.toUpperCase()));
                }

                String accountStatus = rs.getString("accountStatus");
                if (accountStatus != null) {
                    dto.setAccountStatus(AccountStatus.valueOf(accountStatus.toUpperCase()));
                }

                dto.setProvince(rs.getString("province"));
                dto.setDistrict(rs.getString("district"));
                dto.setCity(rs.getString("city"));
                dto.setWard(rs.getInt("ward"));
                dto.setTole(rs.getString("tole"));

                dto.setFatherName(rs.getString("fatherName"));
                dto.setMotherName(rs.getString("motherName"));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to fetch detailed pending approval", e);
            throw new RuntimeException("Failed to fetch approval details", e);
        }

        return dto;
    }

    @Override
    public boolean existsByUserId(Long userId) {
        try (
                Connection con = DbConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(QueryUtil.SELECT_FAMILY_BY_USERID);
        ) {
            ps.setLong(1, userId);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);
        }
        return false;
    }

    @Override
    public boolean deleteByUserId(Long userId, Connection con) {

        try (PreparedStatement ps = con.prepareStatement(QueryUtil.DELETE_ACCOUNT_BY_USERID)) {
            ps.setLong(1, userId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "failed to execute query {e}, ", e);

        }
        return false;
    }
}
