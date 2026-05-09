package com.banking.sathi.exceptions;

public class InvalidTransactionPinException extends RuntimeException {
    public InvalidTransactionPinException(String message) {
        super(message);
    }
}
