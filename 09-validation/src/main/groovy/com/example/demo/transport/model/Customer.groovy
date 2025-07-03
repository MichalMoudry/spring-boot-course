package com.example.demo.transport.model

import com.example.demo.transport.validation.CourseCode
import groovy.transform.CompileStatic
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@CompileStatic
final class Customer {
    private String firstName

    @NotNull(message = 'is required')
    @NotBlank(message = 'at least one character')
    @Size(min = 1, message = 'is required')
    private String lastName

    @Pattern(regexp = '^[a-zA-Z0-9]{5}', message = 'only 5 chars/digits')
    private String postCode

    @CourseCode(value = 'CODE', message = 'must start with CODE')
    private String courseCode

    String getFirstName() { firstName }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() { lastName }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getPostCode() { postCode }

    void setPostCode(String postCode) {
        this.postCode = postCode
    }

    String getCourseCode() { courseCode }

    void setCourseCode(String courseCode) {
        this.courseCode = courseCode
    }
}
