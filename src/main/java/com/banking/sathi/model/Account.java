package com.banking.sathi.model;

import com.banking.sathi.enums.AccountStatus;
import com.banking.sathi.enums.AccountType;

import java.time.LocalDateTime;

public class Account {

    private Long id;

    private Long userId;

    private String accountNumber;

    private String transactionPin;

    private AccountType type;

    private double balance;

    private AccountStatus status;

    private LocalDateTime created;

    public Account(Long id, Long userId, String accountNumber, String transactionPin, AccountType type, double balance, AccountStatus status, LocalDateTime created) {
        this.id = id;
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.transactionPin = transactionPin;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionPin() {
        return transactionPin;
    }

    public void setTransactionPin(String transactionPin) {
        this.transactionPin = transactionPin;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
