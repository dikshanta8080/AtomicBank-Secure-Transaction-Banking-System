package com.banking.sathi.repository;

public interface InterestRepository {
    Double findBalanceByAccountId(Long accountId);
    void applyInterest(Long accountId, Double rate, Double amount);
}
