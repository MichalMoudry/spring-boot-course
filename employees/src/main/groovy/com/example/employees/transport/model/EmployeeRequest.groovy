package com.example.employees.transport.model

import com.example.employees.service.model.EmployeeData
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@CompileStatic
final class EmployeeRequest {
    @NotBlank(message = 'First name is mandatory')
    @Size(min = 3, max = 50, message = 'First name must be between 3 and 50 characters')
    @JsonProperty('first_name')
    String firstName

    @NotBlank(message = 'Last name is mandatory')
    @Size(min = 3, max = 50, message = 'Last name must be between 3 and 50 characters')
    @JsonProperty('last_name')
    String lastName

    @NotBlank(message = 'Email is mandatory')
    @Email(message = 'Please provide a valid email address')
    @JsonProperty('email')
    String email

    EmployeeData toServiceObject() {
        new EmployeeData(firstName, lastName, email)
    }
}
