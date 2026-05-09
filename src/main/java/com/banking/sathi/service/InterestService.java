package com.banking.sathi.service;

import com.banking.sathi.dao.InterestDao;
import com.banking.sathi.exceptions.AccountDoesNotExistsException;
import com.banking.sathi.repository.InterestRepository;

public class InterestService {
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
}
