package com.banking.sathi.exceptions;

public class AccountCreationFailedException extends RuntimeException {
    public AccountCreationFailedException(String message) {
        super(message);
    }

    public AccountCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
