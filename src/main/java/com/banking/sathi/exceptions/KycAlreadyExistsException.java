package com.banking.sathi.exceptions;

public class KycAlreadyExistsException extends RuntimeException {
    public KycAlreadyExistsException(String message) {
        super(message);
    }
}
