package com.example.demo.transport.validation

import groovy.transform.CompileStatic
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

@CompileStatic
final class CourseCodeConstraintValidator
        implements ConstraintValidator<CourseCode, String> {
    private String coursePrefix

    @Override
    void initialize(CourseCode code) {
        coursePrefix = code.value()
    }

    @Override
    boolean isValid(
            String s,
            ConstraintValidatorContext constraintValidatorContext) {
        if (s != null) {
            s.startsWith(coursePrefix)
        }
        else {
            false
        }
    }
}
