package com.banking.sathi.validators;

import com.banking.sathi.model.User;

public class UserValidator {
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

    public static void validateCredentialsForRegistration(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (!user.getEmail().matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (!user.getPassword().matches(PASSWORD_REGEX)) {
            throw new IllegalArgumentException("Invalid password format");
        }

        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address is required");
        }

    }
}
