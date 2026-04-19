package com.banking.sathi.validators;

import com.banking.sathi.model.User;


public class UserValidator {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final String PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

    public static void validateCredentialsForRegistration(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (user.getName() == null || user.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
    }

    public static void validateCredentialsForLogin(String email, String password) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || !email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private static void validatePassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password is required");
        }

        if (!password.matches(PASSWORD_REGEX)) {
            throw new IllegalArgumentException(
                    "Password must be 8-20 chars, including uppercase, lowercase, number, and special character"
            );
        }
    }
}