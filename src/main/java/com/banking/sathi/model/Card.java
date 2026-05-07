package com.banking.sathi.model;

import com.banking.sathi.enums.CardStatus;
import com.banking.sathi.enums.CardType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Card {
    private Long id;
    private Long accountId;
    private String cardNumber;
    private CardType type;
    private CardStatus status;
    private Double creditLimit;
    private LocalDate expiryDate;
    private String cvv;
    private String rejectionReason;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Card() {
    }

    public Card(Long id, Long accountId, String cardNumber, CardType type, CardStatus status, Double creditLimit, LocalDate expiryDate, String cvv, String rejectionReason, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.accountId = accountId;
        this.cardNumber = cardNumber;
        this.type = type;
        this.status = status;
        this.creditLimit = creditLimit;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.rejectionReason = rejectionReason;
        this.created = created;
        this.updated = updated;
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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
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
