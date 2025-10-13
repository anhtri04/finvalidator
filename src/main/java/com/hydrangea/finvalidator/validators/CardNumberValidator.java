/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hydrangea.finvalidator.validators;

import java.util.regex.Pattern;

import com.hydrangea.finvalidator.ValidationResult;
import com.hydrangea.finvalidator.Validator;
import com.hydrangea.finvalidator.utils.Luhn;

/**
 *
 * @author Anh Tri
 */

 /**
 * Credit/debit card basic validator: numeric, typical length, and Luhn check.
 */

public class CardNumberValidator implements Validator<String> {
    private static final Pattern DIGITS_AND_SPACES = Pattern.compile("^[\\d\\s]+$");
    private final int minLen;
    private final int maxLen;

    public CardNumberValidator() {
        this(13, 19); // common card number lengths
    }

    public CardNumberValidator(int minLen, int maxLen) {
        this.minLen = minLen;
        this.maxLen = maxLen;
    }

    @Override
    public ValidationResult validate(String input) {
        if (input == null) return ValidationResult.fail("EMPTY", "Card number is null");
        String s = input.trim();
        if (s.isEmpty()) return ValidationResult.fail("EMPTY", "Card number is empty");
        if (!DIGITS_AND_SPACES.matcher(s).matches()) return ValidationResult.fail("INVALID_CHARS", "Card number contains invalid characters");
        String digits = s.replaceAll("\\s+", "");
        int len = digits.length();
        if (len < minLen || len > maxLen) {
            return ValidationResult.fail("INVALID_LENGTH", "Card number length must be between " + minLen + " and " + maxLen);
        }
        if (!Luhn.check(digits)) return ValidationResult.fail("LUHN_FAIL", "Card number failed Luhn check");
        return ValidationResult.ok();
    }
}
