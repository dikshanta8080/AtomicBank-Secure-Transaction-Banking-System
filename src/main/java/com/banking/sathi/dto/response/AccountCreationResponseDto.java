package com.banking.sathi.dto.response;

public class AccountCreationResponseDto {
    private String userName;
    private String accountNumber;
    private String transactionPin;
    private String message;

    public AccountCreationResponseDto(String userName, String accountNumber, String transactionPin, String message) {
        this.userName = userName;
        this.accountNumber = accountNumber;
        this.transactionPin = transactionPin;
        this.message = message;
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

    public String getTransactionPin() {
        return transactionPin;
    }

    public void setTransactionPin(String transactionPin) {
        this.transactionPin = transactionPin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
