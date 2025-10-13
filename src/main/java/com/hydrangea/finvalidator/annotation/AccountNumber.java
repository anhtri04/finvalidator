/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */

package com.hydrangea.finvalidator.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Anh Tri
 */

 /**
 * Bean Validation annotation to validate account numbers.
 */
@Documented
@Constraint(validatedBy = AccountNumberConstraintValidator.class)
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface AccountNumber {
    String message() default "Invalid account number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int min() default 6;
    int max() default 20;
}
