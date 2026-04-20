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

    public AccountCreationRequest(AccountType accountType, String transactionPin, LocalDate dob, String gender, String citizenship, LocalDate citizenshipIssueDate, String citizenshipDistrict, String phone, String occupation, Double income, String province, String district, String city, Integer ward, String tole, String fatherName, String motherName) {
        this.accountType = accountType;
        this.transactionPin = transactionPin;
        this.dob = dob;
        this.gender = gender;
        this.citizenship = citizenship;
        this.citizenshipIssueDate = citizenshipIssueDate;
        this.citizenshipDistrict = citizenshipDistrict;
        this.phone = phone;
        this.occupation = occupation;
        this.income = income;
        this.province = province;
        this.district = district;
        this.city = city;
        this.ward = ward;
        this.tole = tole;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getTransactionPin() {
        return transactionPin;
    }

    public void setTransactionPin(String transactionPin) {
        this.transactionPin = transactionPin;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
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

    public LocalDate getCitizenshipIssueDate() {
        return citizenshipIssueDate;
    }

    public void setCitizenshipIssueDate(LocalDate citizenshipIssueDate) {
        this.citizenshipIssueDate = citizenshipIssueDate;
    }

    public String getCitizenshipDistrict() {
        return citizenshipDistrict;
    }

    public void setCitizenshipDistrict(String citizenshipDistrict) {
        this.citizenshipDistrict = citizenshipDistrict;
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

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
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

    public Integer getWard() {
        return ward;
    }

    public void setWard(Integer ward) {
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
}
