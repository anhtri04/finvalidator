/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hydrangea.finvalidator.validators;

import java.util.regex.Pattern;

import com.hydrangea.finvalidator.ValidationResult;
import com.hydrangea.finvalidator.Validator;

/**
 *
 * @author Anh Tri
 */

/**
 * Simple account number validator: digits only, length within min..max.
 */

public class AccountNumberValidator implements Validator<String> {
    private static final Pattern DIGITS = Pattern.compile("^\\d+$");
    private final int minLen;
    private final int maxLen;

    public AccountNumberValidator() {
        this(6, 20);
    }

    public AccountNumberValidator(int minLen, int maxLen) {
        if (minLen <= 0 || maxLen <= 0 || minLen > maxLen) {
            throw new IllegalArgumentException("invalid length bounds");
        }
        this.minLen = minLen;
        this.maxLen = maxLen;
    }

    @Override
    public ValidationResult validate(String input) {
        if (input == null) {
            return ValidationResult.fail("EMPTY", "Account number is null");
        }
        String s = input.trim().replaceAll("\\s+", "");
        if (s.isEmpty()) {
            return ValidationResult.fail("EMPTY", "Account number is empty");
        }
        if (!DIGITS.matcher(s).matches()) {
            return ValidationResult.fail("NOT_NUMERIC", "Account number must contain digits only");
        }
        int len = s.length();
        if (len < minLen || len > maxLen) {
            return ValidationResult.fail("INVALID_LENGTH", "Account number length must be between " + minLen + " and " + maxLen);
        }
        return ValidationResult.ok();
    }
}
