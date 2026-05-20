package com.banking.sathi.utils;

public class AccountNumberGenerator {
    public static String generateUniqueAccountNumber() {
        long timeMillis = System.currentTimeMillis();
        return "ACC" + timeMillis;
    }


}
