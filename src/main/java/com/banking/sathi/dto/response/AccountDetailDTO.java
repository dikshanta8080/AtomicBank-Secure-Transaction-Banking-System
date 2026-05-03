package com.banking.sathi.dto.response;

import com.banking.sathi.enums.AccountStatus;
import com.banking.sathi.enums.AccountType;
import com.banking.sathi.enums.KycStatus;

public class AccountDetailDTO {
    // User
    private Long userId;
    private String name;
    private String email;

    // KYC
    private String dob;
    private String gender;
    private String citizenship;
    private String phone;
    private String occupation;
    private KycStatus kycStatus;

    // Account
    private String accountNumber;
    private AccountType accountType;
    private AccountStatus accountStatus;

    // Address
    private String province;
    private String district;
    private String city;
    private int ward;
    private String tole;

    // Family
    private String fatherName;
    private String motherName;

    public AccountDetailDTO() {
    }

    public AccountDetailDTO(Long userId, String name, String email, String dob, String gender, String citizenship, String phone, String occupation, KycStatus kycStatus, String accountNumber, AccountType accountType, AccountStatus accountStatus, String province, String district, String city, int ward, String tole, String fatherName, String motherName) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.citizenship = citizenship;
        this.phone = phone;
        this.occupation = occupation;
        this.kycStatus = kycStatus;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.province = province;
        this.district = district;
        this.city = city;
        this.ward = ward;
        this.tole = tole;
        this.fatherName = fatherName;
        this.motherName = motherName;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public KycStatus getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(KycStatus kycStatus) {
        this.kycStatus = kycStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getWard() {
        return ward;
    }

    public void setWard(int ward) {
        this.ward = ward;
    }

    public String getTole() {
        return tole;
    }

    public void setTole(String tole) {
        this.tole = tole;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    @Override
    public String toString() {
        return "AccountDetailDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", phone='" + phone + '\'' +
                ", occupation='" + occupation + '\'' +
                ", kycStatus=" + kycStatus +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountType=" + accountType +
                ", accountStatus=" + accountStatus +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", ward=" + ward +
                ", tole='" + tole + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                '}';
    }
}
