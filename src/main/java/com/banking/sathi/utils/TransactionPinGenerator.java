package com.banking.sathi.utils;

import java.util.Random;

public class TransactionPinGenerator {
    
    public static String generateTransactionPin() {
        Random random = new Random();
        int pin = 1000 + random.nextInt(9000);
        return String.valueOf(pin);
    }
}
