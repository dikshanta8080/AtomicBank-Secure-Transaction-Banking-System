package com.banking.sathi.exceptions;

public class AccountDeletionFailedException extends RuntimeException {
    public AccountDeletionFailedException(String message) {
        super(message);
    }
}
