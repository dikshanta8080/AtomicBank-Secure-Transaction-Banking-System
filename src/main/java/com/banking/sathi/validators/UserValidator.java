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
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }
        validateEmailAndPassword(user.getEmail(), user.getPassword());
    }

    public static void validateCredentialsForLogin(String email, String password) {
        validateEmailAndPassword(email, password);
    }

    private static void validateEmailAndPassword(String email, String password) {
        if (!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new IllegalArgumentException("Invalid password format");
        }
    }
}
