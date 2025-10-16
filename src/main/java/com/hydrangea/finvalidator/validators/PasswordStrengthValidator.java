/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hydrangea.finvalidator.validators;

import com.hydrangea.finvalidator.ValidationResult;
import com.hydrangea.finvalidator.Validator;

/**
 * Validates password strength based on configurable requirements.
 *
 * @author Anh Tri
 */
public class PasswordStrengthValidator implements Validator<String> {
    private static final int DEFAULT_MIN_LENGTH = 8;
    private static final int DEFAULT_MAX_LENGTH = 128;
    private final int minLength;
    private final int maxLength;
    private final boolean requireUpperCase;
    private final boolean requireLowerCase;
    private final boolean requireDigits;
    private final boolean requireSpecialChars;

    public PasswordStrengthValidator() {
        this(DEFAULT_MIN_LENGTH, DEFAULT_MAX_LENGTH, true, true, true, true);
    }

    public PasswordStrengthValidator(int minLength, boolean requireUpperCase, boolean requireLowerCase, 
                                    boolean requireDigits, boolean requireSpecialChars) {
        this(minLength, DEFAULT_MAX_LENGTH, requireUpperCase, requireLowerCase, requireDigits, requireSpecialChars);
    }

    public PasswordStrengthValidator(int minLength, int maxLength, boolean requireUpperCase, 
                                    boolean requireLowerCase, boolean requireDigits, boolean requireSpecialChars) {
        if (minLength < 1) {
            throw new IllegalArgumentException("Minimum length must be at least 1");
        }
        if (maxLength < minLength) {
            throw new IllegalArgumentException("Maximum length must be greater than or equal to minimum length");
        }
        
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.requireUpperCase = requireUpperCase;
        this.requireLowerCase = requireLowerCase;
        this.requireDigits = requireDigits;
        this.requireSpecialChars = requireSpecialChars;
    }

    @Override
    public ValidationResult validate(String input) {
        if (input == null) {
            return ValidationResult.fail("EMPTY", "Password is null");
        }
        
        if (input.isEmpty()) {
            return ValidationResult.fail("EMPTY", "Password is empty");
        }
        
        if (input.length() < minLength) {
            return ValidationResult.fail("TOO_SHORT", "Password must be at least " + minLength + " characters long");
        }
        
        if (input.length() > maxLength) {
            return ValidationResult.fail("TOO_LONG", "Password must not exceed " + maxLength + " characters");
        }
        
        // Single pass through the password to check all character requirements
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        
        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecialChar = true;
            }
        }
        
        if (requireUpperCase && !hasUpperCase) {
            return ValidationResult.fail("MISSING_UPPERCASE", "Password must contain at least one uppercase letter");
        }
        
        if (requireLowerCase && !hasLowerCase) {
            return ValidationResult.fail("MISSING_LOWERCASE", "Password must contain at least one lowercase letter");
        }
        
        if (requireDigits && !hasDigit) {
            return ValidationResult.fail("MISSING_DIGIT", "Password must contain at least one digit");
        }
        
        if (requireSpecialChars && !hasSpecialChar) {
            return ValidationResult.fail("MISSING_SPECIAL", "Password must contain at least one special character");
        }
        
        return ValidationResult.ok();
    }
}