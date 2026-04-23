package com.banking.sathi.utils;

public class AccountNumberGenerator {
    public static String generateUniqueAccountNumber() {
        long timeMillis = System.currentTimeMillis();
        return "ACC" + timeMillis;
    }

    public static void main(String[] args) {
        System.out.println(generateUniqueAccountNumber());

    }
}
