package com.example.demo.transport.validation

import groovy.transform.CompileStatic
import jakarta.validation.Constraint
import jakarta.validation.Payload

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@CompileStatic
@Target([ElementType.METHOD, ElementType.FIELD])
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@interface CourseCode {
    String value() default 'CODE'
    String message() default 'must start with CODE'
    Class<?>[] groups() default []
    Class<? extends Payload>[] payload() default []
}