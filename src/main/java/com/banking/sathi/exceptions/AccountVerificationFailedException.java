package com.banking.sathi.exceptions;

public class AccountVerificationFailedException extends RuntimeException {
    public AccountVerificationFailedException(String message) {
        super(message);
    }
}
