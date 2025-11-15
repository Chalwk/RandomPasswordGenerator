/**
 * Random Password Generator
 * Copyright (c) 2025 Jericho Crosby (Chalwk)
 * <p>
 * This project is licensed under the MIT License.
 * See LICENSE file for details.
 */

package com.chalwk.util;

import com.chalwk.model.PasswordConfig;

import java.security.SecureRandom;
import java.util.*;

public class PasswordGeneratorEngine {
    private static final SecureRandom random = new SecureRandom();

    // Character sets
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    // Characters to exclude
    private static final String SIMILAR_CHARS = "il1Lo0O";
    private static final String AMBIGUOUS_CHARS = "{}[]()|`~;:,.<>";

    public static String generatePassword(PasswordConfig config) {
        validateConfig(config);

        StringBuilder charPool = new StringBuilder();
        StringBuilder password = new StringBuilder();

        // Build character pool based on configuration
        if (config.isIncludeUppercase()) {
            String uppercase = filterCharacters(UPPERCASE, config);
            charPool.append(uppercase);
            // Ensure at least one uppercase if selected
            if (!uppercase.isEmpty()) {
                password.append(getRandomChar(uppercase));
            }
        }

        if (config.isIncludeLowercase()) {
            String lowercase = filterCharacters(LOWERCASE, config);
            charPool.append(lowercase);
            if (!lowercase.isEmpty()) {
                password.append(getRandomChar(lowercase));
            }
        }

        if (config.isIncludeNumbers()) {
            String numbers = filterCharacters(NUMBERS, config);
            charPool.append(numbers);
            if (!numbers.isEmpty()) {
                password.append(getRandomChar(numbers));
            }
        }

        if (config.isIncludeSymbols()) {
            String symbols = filterCharacters(SYMBOLS, config);
            charPool.append(symbols);
            if (!symbols.isEmpty()) {
                password.append(getRandomChar(symbols));
            }
        }

        // Validate that we have characters to work with
        if (charPool.length() == 0) {
            throw new IllegalArgumentException("No character sets selected for password generation");
        }

        // Fill remaining characters
        int remainingLength = config.getLength() - password.length();
        for (int i = 0; i < remainingLength; i++) {
            password.append(getRandomChar(charPool.toString()));
        }

        // Shuffle the password to randomize character positions
        return shuffleString(password.toString());
    }

    private static String filterCharacters(String characters, PasswordConfig config) {
        StringBuilder filtered = new StringBuilder();

        for (char c : characters.toCharArray()) {
            boolean exclude = false;

            if (config.isExcludeSimilar() && SIMILAR_CHARS.indexOf(c) != -1) {
                exclude = true;
            }

            if (config.isExcludeAmbiguous() && AMBIGUOUS_CHARS.indexOf(c) != -1) {
                exclude = true;
            }

            if (!exclude) {
                filtered.append(c);
            }
        }

        return filtered.toString();
    }

    private static char getRandomChar(String characters) {
        if (characters.isEmpty()) {
            throw new IllegalArgumentException("Character pool is empty");
        }
        return characters.charAt(random.nextInt(characters.length()));
    }

    private static String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters, random);

        StringBuilder result = new StringBuilder();
        for (char c : characters) {
            result.append(c);
        }
        return result.toString();
    }

    private static void validateConfig(PasswordConfig config) {
        if (config.getLength() < 1) {
            throw new IllegalArgumentException("Password length must be at least 1");
        }

        if (!config.isIncludeUppercase() && !config.isIncludeLowercase() &&
                !config.isIncludeNumbers() && !config.isIncludeSymbols()) {
            throw new IllegalArgumentException("At least one character set must be selected");
        }
    }

    public static double calculateEntropy(String password) {
        if (password.isEmpty()) return 0;

        Set<Character> uniqueChars = new HashSet<>();
        for (char c : password.toCharArray()) {
            uniqueChars.add(c);
        }

        int poolSize = uniqueChars.size();
        return Math.log(Math.pow(poolSize, password.length())) / Math.log(2);
    }
}