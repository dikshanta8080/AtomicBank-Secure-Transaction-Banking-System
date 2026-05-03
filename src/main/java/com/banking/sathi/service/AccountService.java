package com.banking.sathi.service;

import com.banking.sathi.dao.*;
import com.banking.sathi.dto.request.AccountCreationRequest;
import com.banking.sathi.dto.response.AccountCreationResponseDto;
import com.banking.sathi.dto.response.AccountDetailDTO;
import com.banking.sathi.dto.response.AccountListDTO;
import com.banking.sathi.enums.*;
import com.banking.sathi.exceptions.*;
import com.banking.sathi.mapper.request.AddressMapper;
import com.banking.sathi.mapper.request.FamilyMapper;
import com.banking.sathi.mapper.request.KycMapper;
import com.banking.sathi.model.*;
import com.banking.sathi.repository.*;
import com.banking.sathi.utils.AccountNumberGenerator;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.TransactionPinGenerator;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountService {

    private static final Double SAVINGS_OPENING_BALANCE = 2000d;
    private static final Double CURRENT_OPENING_BALANCE = 1000d;
    private static final Logger logger = Logger.getLogger(AccountService.class.getName());
    private final KycRepository kycRepository;
    private final FamilyRepository familyRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final KycMapper kycMapper;
    private final AddressMapper addressMapper;
    private final FamilyMapper familyMapper;

    public AccountService() {
        this.kycRepository = new KycDao();
        this.familyRepository = new FamilyDao();
        this.accountRepository = new AccountDao();
        this.addressRepository = new AddressDao();
        this.userRepository = new UserDao();

        this.kycMapper = new KycMapper();
        this.addressMapper = new AddressMapper();
        this.familyMapper = new FamilyMapper();
    }

    public AccountCreationResponseDto createAccount(AccountCreationRequest request, Long userId) {
        Connection con = null;
        Family family = familyMapper.apply(request);
        Address address = addressMapper.apply(request);
        Kyc kyc = kycMapper.apply(request);
        kyc.setStatus(KycStatus.PENDING);

        try {
            con = DbConnection.getConnection();
            con.setAutoCommit(false);

            User user = userRepository.findById(userId, con)
                    .orElseThrow(() -> new UserDoesnotExistsException(
                            "You are not authorized to perform this operation"
                    ));
            if (user.getRole() != Role.USER) {
                throw new UnauthorizedAccessException("Admin can not create account");
            }
            if (user.getUserStatus() == UserStatus.BLOCKED) {
                throw new AccountCreationFailedException("Failed to create account");
            }
            if (kycRepository.existsByCitizenship(request.getCitizenship(), con)) {
                throw new KycAlreadyExistsException(
                        "KYC with the provided details already exists"
                );
            }

            if (accountRepository.existsByUserId(userId, con)
                    || addressRepository.existsByUserId(userId, con)
                    || familyRepository.existsByUserId(userId, con)
                    || kycRepository.existsByUserId(userId, con)) {
                throw new AccountAlreadyExistsException("Account with this details already exists");
            }

            String transactionPin = TransactionPinGenerator.generateTransactionPin();
            String accountNumber = AccountNumberGenerator.generateUniqueAccountNumber();
            String hashedTransactionPin = BCrypt.hashpw(transactionPin, BCrypt.gensalt(11));
            Double openingBalance = request.getAccountType().equals(AccountType.SAVINGS) ? SAVINGS_OPENING_BALANCE : CURRENT_OPENING_BALANCE;
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setTransactionPin(hashedTransactionPin);
            account.setType(request.getAccountType());
            account.setBalance(openingBalance);
            account.setStatus(AccountStatus.INACTIVE);

            family.setUserId(userId);
            address.setUserId(userId);
            kyc.setUserId(userId);
            account.setUserId(userId);

            int saveFamily = familyRepository.saveFamily(family, con);
            int saveAddress = addressRepository.saveAddress(address, con);
            int saveKyc = kycRepository.saveKyc(kyc, con);
            int saveAccount = accountRepository.saveAccount(account, con);

            if (saveFamily <= 0 || saveAddress <= 0 || saveKyc <= 0 || saveAccount <= 0) {
                throw new AccountCreationFailedException(
                        "Failed to create account due to database error"
                );
            }
            con.commit();

            return new AccountCreationResponseDto(
                    user.getName(),
                    account.getAccountNumber(),
                    transactionPin,
                    "Please change the transaction pin ASAP!"
            );

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Exception occurred", e);
            if (con != null) {
                try {
                    con.rollback();
                    logger.info("Transaction rolled back");
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, "Rollback failed", ex);
                }
            }
            throw new AccountCreationFailedException(
                    "Database error occurred!"

            );

        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Failed to close connection", e);
                }
            }
        }
    }

    public boolean deleteAccount(Long id) {
        return accountRepository.deleteAccountById(id) > 0;
    }

    public boolean verifyAccount(Long userId) {
        Connection con = null;
        try {
            con = DbConnection.getConnection();
            con.setAutoCommit(false);
            Account account = accountRepository.findByUserId(userId, con).orElseThrow(() ->
                    new AccountVerificationFailedException("Account does not exists exception"));
            Kyc kyc = kycRepository.findByUserId(userId, con).orElseThrow(() ->
                    new AccountVerificationFailedException("Kyc does not exists exception"));

            boolean isKycVerified = kycRepository.verifyKyc(kyc.getId(), con);
            boolean isAccountVerified = accountRepository.verifyAccount(account.getId(), con);
            if (!isKycVerified || !isAccountVerified) {
                throw new AccountVerificationFailedException("Failed to verify the Details");
            }
            con.commit();
            return true;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                    logger.info("Transaction rolled back");
                } catch (SQLException ex) {
                    logger.log(Level.SEVERE, "Rollback failed", ex);
                }
            }
            throw new AccountCreationFailedException(
                    "Database error occurred!"

            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Failed to close connection", e);
                }
            }
        }
    }

    public List<AccountListDTO> getPendingApprovalAccounts() {
        return accountRepository.getPendingAccounts();
    }

    public AccountDetailDTO getPendingApprovalAccountDetails(Long userId) {
        return accountRepository.getDetailedPendingApproval(userId);
    }
}
