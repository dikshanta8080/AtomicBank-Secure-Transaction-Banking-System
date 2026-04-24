package com.banking.sathi.service;

import com.banking.sathi.dao.*;
import com.banking.sathi.dto.request.AccountCreationRequest;
import com.banking.sathi.dto.response.AccountCreationResponseDto;
import com.banking.sathi.enums.AccountStatus;
import com.banking.sathi.enums.KycStatus;
import com.banking.sathi.exceptions.AccountCreationFailedException;
import com.banking.sathi.exceptions.KycAlreadyExistsException;
import com.banking.sathi.exceptions.UserDoesnotExistsException;
import com.banking.sathi.mapper.request.AddressMapper;
import com.banking.sathi.mapper.request.FamilyMapper;
import com.banking.sathi.mapper.request.KycMapper;
import com.banking.sathi.model.*;
import com.banking.sathi.repository.*;
import com.banking.sathi.utils.AccountNumberGenerator;
import com.banking.sathi.utils.DbConnection;
import com.banking.sathi.utils.TransactionPinGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountService {

    private static final Double openingBalance = 1000d;
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

            if (kycRepository.existsByCitizenship(request.getCitizenship(), con)) {
                throw new KycAlreadyExistsException(
                        "KYC with the provided details already exists"
                );
            }
            /*
            Ya validation garna baki xa.if kycRepo.existsByUserId-> throw exception called
             details already exists could not create account,something like that
             */
            Account account = new Account();
            account.setAccountNumber(AccountNumberGenerator.generateUniqueAccountNumber());
            account.setTransactionPin(TransactionPinGenerator.generateTransactionPin());
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
                    account.getTransactionPin(),
                    "Please change the transaction pin ASAP!"
            );

        } catch (Exception e) {
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
                    "Account creation failed"

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
}
