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
public class EmailValidatorTest {

    // SUCCESS CASE
    @Test
    void validEmail() {
        EmailValidator v = new EmailValidator();
        ValidationResult r = v.validate("hydrangea@gmail.com");
        assertTrue(r.isValid());
    }

    // FAILURE CASE
    @Test
    void invalidEmail() {
        EmailValidator v = new EmailValidator();
        ValidationResult r = v.validate("hydrangea#gmail/com");
        assertFalse(r.isValid());
        assertEquals("INVALID", r.getCode());
    }

    // FAILURE CASE
    @Test
    void emptyEmail() {
        EmailValidator v = new EmailValidator();
        ValidationResult r = v.validate("    ");
        assertFalse(r.isValid());
        assertEquals("EMPTY", r.getCode());
    }

    // FAILURE CASE
    @Test
    void tooLongEmail() {
        EmailValidator v = new EmailValidator();
        ValidationResult r = v.validate("a".repeat(300) + "@gmail.com");
        assertFalse(r.isValid());
        assertEquals("TOO_LONG", r.getCode());
    }

    // FAILURE CASE
    @Test
    void nullEmail() {
        EmailValidator v = new EmailValidator();
        ValidationResult r = v.validate(null);
        assertFalse(r.isValid());
        assertEquals("NULL", r.getCode());
    }
}