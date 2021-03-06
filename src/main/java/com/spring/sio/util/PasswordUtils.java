package com.spring.sio.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PasswordUtils {

    private final Random RANDOM = new Random();
    private final String ALPHABET = "ABCDEFGHIJKLAMOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private final int ITERATIONS = 1000;
    private final int KEY_LENGTH = 256;

    public String generateUserId(int length) {
        return generateRandomString(length);

    }

    public String generateAddressId(int length) {
        return generateRandomString(length);
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}
