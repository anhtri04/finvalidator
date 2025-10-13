package com.hydrangea.finvalidator.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.hydrangea.finvalidator.ValidationResult;

public class CardNumberValidatorTest {
    // SUCCESS CASE
    @Test
    public void testValidate() {
        CardNumberValidator validator = new CardNumberValidator();
        ValidationResult result = validator.validate("4111 1111 1111 1111");
        assertEquals(ValidationResult.ok(), result);
    }

    // FAILURE CASE
    @Test
    public void testValidateInvalid() {
        CardNumberValidator validator = new CardNumberValidator();
        ValidationResult result = validator.validate("4111 1111 1111 1112");
        assertEquals(ValidationResult.fail("LUHN_FAIL", "Card number failed Luhn check"), result);
    }
}
