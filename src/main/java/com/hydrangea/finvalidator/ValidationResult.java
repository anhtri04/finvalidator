/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.hydrangea.finvalidator;

import com.hydrangea.finvalidator.exceptions.ValidationException;

/**
 *
 * @author Anh Tri
 */
public final class ValidationResult {
    private final boolean valid;
    private final String code;
    private final String message;

    private ValidationResult(boolean valid, String code, String message) {
        this.valid = valid;
        this.code = code;
        this.message = message;
    }

    public static ValidationResult ok() {
        return new ValidationResult(true, "OK", "Valid");
    }

    public static ValidationResult fail(String code, String message) {
        return new ValidationResult(false, code, message);
    }

    public boolean isValid() {
        return valid;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Convenience: throw runtime exception if invalid.
     */
    public void throwIfInvalid() {
        if (!valid) {
            throw new ValidationException(code, message);
        }
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "valid=" + valid +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
