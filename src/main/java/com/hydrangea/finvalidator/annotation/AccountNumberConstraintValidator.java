/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hydrangea.finvalidator.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hydrangea.finvalidator.validators.AccountNumberValidator;

/**
 *
 * @author Anh Tri
 */
public class AccountNumberConstraintValidator implements ConstraintValidator<AccountNumber, String> {
    private int min;
    private int max;

    @Override
    public void initialize(AccountNumber constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        AccountNumberValidator validator = new AccountNumberValidator(min, max);
        return validator.validate(value).isValid();
    }
}
