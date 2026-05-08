package com.banking.sathi.utils;

public class CardNumberGenerator {
    public static String generateCardNumber() {
        return String.valueOf(System.currentTimeMillis());
    }
}
