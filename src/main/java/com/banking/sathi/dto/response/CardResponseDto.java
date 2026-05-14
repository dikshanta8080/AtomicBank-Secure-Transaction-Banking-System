package com.banking.sathi.dto.response;

import com.banking.sathi.enums.CardStatus;
import com.banking.sathi.enums.CardType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CardResponseDto {
    private Long id;
    private Long accountId;
    private String cardNumber;
    private CardType type;
    private CardStatus status;
    private Double creditLimit;
    private Double monthlyIncome;
    private LocalDate expiryDate;
    private LocalDateTime appliedDate;
    private String rejectionReason;
    private String userName;
    private String accountNumber;

    public CardResponseDto() {
     
    }

    public CardResponseDto(Long id, Long accountId, String cardNumber, CardType type, CardStatus status, Double creditLimit, Double monthlyIncome, LocalDate expiryDate, LocalDateTime appliedDate) {
        this.id = id;
        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.type = type;
        this.status = status;
        this.creditLimit = creditLimit;
        this.monthlyIncome = monthlyIncome;
        this.expiryDate = expiryDate;
        this.appliedDate = appliedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDateTime getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDateTime appliedDate) {
        this.appliedDate = appliedDate;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
