/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package com.hydrangea.finvalidator.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.hydrangea.finvalidator.ValidationResult;
 
/**
 *
 * @author Anh Tri
 */
public class AccountNumberValidatorTest {

    // SUCCESS CASE
    @Test
    void validAccount() {
        AccountNumberValidator v = new AccountNumberValidator(6, 12);
        ValidationResult r = v.validate("123456");
        assertTrue(r.isValid());
    }

    // FAILURE CASE
    @Test
    void emptyAccount() {
        AccountNumberValidator v = new AccountNumberValidator();
        ValidationResult r = v.validate("   ");
        assertFalse(r.isValid());
        assertEquals("EMPTY", r.getCode());
    }

    // FAILURE CASE
    @Test
    void nonNumeric() {
        AccountNumberValidator v = new AccountNumberValidator();
        ValidationResult r = v.validate("12A456");
        assertFalse(r.isValid());
        assertEquals("NOT_NUMERIC", r.getCode());
    }

    // FAILURE CASE
    @Test
    void tooShort() {
        AccountNumberValidator v = new AccountNumberValidator(6, 10);
        ValidationResult r = v.validate("12345");
        assertFalse(r.isValid());
        assertEquals("INVALID_LENGTH", r.getCode());
    }

}