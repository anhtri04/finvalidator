/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Liceses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/AnnotationType.java to edit this template
 */

package com.hydrangea.finvalidator.annotation;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Bean Validation annotation to validate email addresses.
 *
 * @author Anh Tri
 */
@Documented
@Constraint(validatedBy = EmailConstraintValidator.class)
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface Email {
    String message() default "Invalid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}