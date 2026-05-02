package com.banking.sathi.dto.response;

import com.banking.sathi.enums.AccountStatus;
import com.banking.sathi.enums.AccountType;
import com.banking.sathi.enums.KycStatus;

public class AccountListDTO {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private KycStatus kycStatus;
    private AccountType accountType;
    private AccountStatus accountStatus;

    public AccountListDTO(Long userId, String name, String email, String phone, KycStatus kycStatus, AccountType accountType, AccountStatus accountStatus) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.kycStatus = kycStatus;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
    }

    public AccountListDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public KycStatus getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(KycStatus kycStatus) {
        this.kycStatus = kycStatus;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}
