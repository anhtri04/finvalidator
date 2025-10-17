/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hydrangea.finvalidator.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hydrangea.finvalidator.validators.EmailValidator;

/**
 * Constraint validator for email addresses.
 *
 * @author Anh Tri
 */
public class EmailConstraintValidator implements ConstraintValidator<Email, String> {

    private EmailValidator emailValidator;

    @Override
    public void initialize(Email constraintAnnotation) {
        // Initialize the EmailValidator instance
        emailValidator = new EmailValidator();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Null values are considered valid (use @NotNull separately if needed)
        if (value == null) {
            return true;
        }
        
        // Delegate to the EmailValidator implementation
        return emailValidator.validate(value).isValid();
    }
}