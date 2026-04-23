package com.banking.sathi.exceptions;

public class KycDetailsExistsException extends RuntimeException {
    public KycDetailsExistsException(String message) {
        super(message);
    }
}
