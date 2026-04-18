package com.banking.sathi.model;

import com.banking.sathi.enums.TransactionStatus;
import com.banking.sathi.enums.TransactionType;

import java.time.LocalDateTime;

public class Transaction {

    private Long id;
    private Long fromAccountId;
    private Long toAccountId;

    private TransactionType type;

    private Double amount;
    private TransactionStatus status;

    private String remarks;
    private LocalDateTime created;

    public Transaction() {
    }

    public Transaction(Long fromAccountId, Long toAccountId, TransactionType type, Double amount, TransactionStatus status, String remarks) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.remarks = remarks;
    }

    public Transaction(Long id, Long fromAccountId, Long toAccountId, TransactionType type, Double amount, TransactionStatus status, String remarks, LocalDateTime created) {
        this.id = id;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.remarks = remarks;
        this.created = created;
    }

    public Transaction(Long fromAccountId, Long toAccountId, TransactionType type, Double amount, TransactionStatus status, String remarks, LocalDateTime created) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.remarks = remarks;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}

