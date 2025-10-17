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
public class EmailValidator implements Validator<String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    @Override
    public ValidationResult validate(String input) {
        if (input == null) {
            return ValidationResult.fail("NULL", "Email is null");
        }

        String email = input.trim();

        if (email.isEmpty()) {
            return ValidationResult.fail("EMPTY", "Email is empty");
        }
        
        if (email.length() > 254) {
            return ValidationResult.fail("TOO_LONG", "Email is too long");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return ValidationResult.fail("INVALID", "Email format is invalid");
        }

        return ValidationResult.ok();
    }
}
