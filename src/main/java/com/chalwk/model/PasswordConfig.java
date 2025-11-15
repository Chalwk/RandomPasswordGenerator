/**
 * Random Password Generator
 * Copyright (c) 2025 Jericho Crosby (Chalwk)
 * <p>
 * This project is licensed under the MIT License.
 * See LICENSE file for details.
 */

package com.chalwk.model;

public class PasswordConfig {
    private final int length;
    private final boolean includeUppercase;
    private final boolean includeLowercase;
    private final boolean includeNumbers;
    private final boolean includeSymbols;
    private final boolean excludeSimilar;
    private final boolean excludeAmbiguous;
    private final int entropyLevel;

    public PasswordConfig(int length, boolean includeUppercase, boolean includeLowercase,
                          boolean includeNumbers, boolean includeSymbols, boolean excludeSimilar,
                          boolean excludeAmbiguous, int entropyLevel) {
        this.length = length;
        this.includeUppercase = includeUppercase;
        this.includeLowercase = includeLowercase;
        this.includeNumbers = includeNumbers;
        this.includeSymbols = includeSymbols;
        this.excludeSimilar = excludeSimilar;
        this.excludeAmbiguous = excludeAmbiguous;
        this.entropyLevel = entropyLevel;
    }

    // Getters
    public int getLength() {
        return length;
    }

    public boolean isIncludeUppercase() {
        return includeUppercase;
    }

    public boolean isIncludeLowercase() {
        return includeLowercase;
    }

    public boolean isIncludeNumbers() {
        return includeNumbers;
    }

    public boolean isIncludeSymbols() {
        return includeSymbols;
    }

    public boolean isExcludeSimilar() {
        return excludeSimilar;
    }

    public boolean isExcludeAmbiguous() {
        return excludeAmbiguous;
    }
}