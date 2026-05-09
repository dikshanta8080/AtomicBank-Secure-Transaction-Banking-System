package com.banking.sathi.repository;

public interface InterestRepository {
    Double findBalanceByAccountId(Long accountId);
}
