package com.banking.sathi.exceptions;

public class KycDoesnotExistsException extends RuntimeException {
    public KycDoesnotExistsException(String message) {
        super(message);
    }
}
