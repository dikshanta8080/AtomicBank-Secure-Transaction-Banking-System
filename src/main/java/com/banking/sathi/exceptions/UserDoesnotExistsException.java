package com.banking.sathi.exceptions;

public class UserDoesnotExistsException extends RuntimeException {
    public UserDoesnotExistsException(String message) {
        super(message);
    }
}
