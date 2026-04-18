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
}
