package com.banking.sathi.service;

import com.banking.sathi.dao.InterestDao;
import com.banking.sathi.exceptions.AccountDoesNotExistsException;
import com.banking.sathi.repository.InterestRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InterestService {
    private static final Logger logger = Logger.getLogger(InterestService.class.getName());
    private static final double INTEREST_RATE = 3.5d;
    private final InterestRepository interestRepository;

    public InterestService() {
        this.interestRepository = new InterestDao();
    }

    public double calculateSimpleInterest(Long accountId) {
        if (accountId == null || accountId <= 0) {
            throw new IllegalArgumentException("Invalid account id");
        }
        Double balance = interestRepository.findBalanceByAccountId(accountId);
        if (balance == null) {
            throw new AccountDoesNotExistsException("Account not found");
        }
        return (balance * INTEREST_RATE) / 100;
    }

    public void applyInterest(Long accountId) {
        double amount = calculateSimpleInterest(accountId);
        if (amount <= 0) {
            throw new IllegalStateException("Interest amount is zero — account balance may be empty.");
        }
        logger.log(Level.INFO, "Applying interest: accountId={0}, rate={1}, amount={2}",
                new Object[]{accountId, INTEREST_RATE, amount});
        interestRepository.applyInterest(accountId, INTEREST_RATE, amount);
    }
}
