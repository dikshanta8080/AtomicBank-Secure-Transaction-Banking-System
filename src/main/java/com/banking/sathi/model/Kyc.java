package com.banking.sathi.model;

import com.banking.sathi.enums.Gender;
import com.banking.sathi.enums.KycStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Kyc {
    private Long id;
    private Long userId;

    private LocalDate dob;
    private Gender gender;

    private String citizenship;
    private LocalDate issueDate;
    private String district;

    private String phone;
    private String occupation;
    private Double income;

    private KycStatus status;
    private String reason;

    private LocalDateTime created;
    private LocalDateTime updated;

    public Kyc() {
    }

    public Kyc(LocalDate dob, Gender gender, String citizenship, LocalDate issue, String district, String phone, String occupation, Double income) {
        this.dob = dob;
        this.gender = gender;
        this.citizenship = citizenship;
        this.issueDate = issue;
        this.district = district;
        this.phone = phone;
        this.occupation = occupation;
        this.income = income;
    }

    public Kyc(Long userId, LocalDate dob, Gender gender, String citizenship, LocalDate issue, String district, String phone, String occupation, Double income, KycStatus status, String reason) {
        this.userId = userId;
        this.dob = dob;
        this.gender = gender;
        this.citizenship = citizenship;
        this.issueDate = issue;
        this.district = district;
        this.phone = phone;
        this.occupation = occupation;
        this.income = income;
        this.status = status;
        this.reason = reason;
    }

    public Kyc(Long userId, LocalDate dob, Gender gender, String citizenship, LocalDate issue, String district, String phone, String occupation, Double income, KycStatus status, String reason, LocalDateTime created, LocalDateTime updated) {
        this.userId = userId;
        this.dob = dob;
        this.gender = gender;
        this.citizenship = citizenship;
        this.issueDate = issue;
        this.district = district;
        this.phone = phone;
        this.occupation = occupation;
        this.income = income;
        this.status = status;
        this.reason = reason;
        this.created = created;
        this.updated = updated;
    }

    public Kyc(Long id, Long userId, LocalDate dob, Gender gender, String citizenship, LocalDate issue, String district, String phone, String occupation, Double income, KycStatus status, String reason, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.userId = userId;
        this.dob = dob;
        this.gender = gender;
        this.citizenship = citizenship;
        this.issueDate = issue;
        this.district = district;
        this.phone = phone;
        this.occupation = occupation;
        this.income = income;
        this.status = status;
        this.reason = reason;
        this.created = created;
        this.updated = updated;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public LocalDate getIssue() {
        return issueDate;
    }

    public void setIssue(LocalDate issue) {
        this.issueDate = issue;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public KycStatus getStatus() {
        return status;
    }

    public void setStatus(KycStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}