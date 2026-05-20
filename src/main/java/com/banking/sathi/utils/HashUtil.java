package com.banking.sathi.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtil {


    public static String hash(String plainText) {
        if (plainText == null) {
            throw new IllegalArgumentException("Text to hash cannot be null");
        }
        return BCrypt.hashpw(plainText, BCrypt.gensalt(11));
    }

    public static boolean check(String plainText, String hashedValue) {
        if (plainText == null || hashedValue == null) {
            return false;
        }
        return BCrypt.checkpw(plainText, hashedValue);
    }
}
