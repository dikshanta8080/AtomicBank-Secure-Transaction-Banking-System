package com.banking.sathi.dto.request;

import com.banking.sathi.enums.AccountType;

import java.time.LocalDate;

public class AccountCreationRequest {
    private AccountType accountType;
    private String transactionPin;

    // KYC
    private LocalDate dob;
    private String gender;
    private String citizenship;
    private LocalDate citizenshipIssueDate;
    private String citizenshipDistrict;
    private String phone;
    private String occupation;
    private Double income;

    // Address
    private String province;
    private String district;
    private String city;
    private Integer ward;
    private String tole;


    // Family
    private String fatherName;
    private String motherName;
}
