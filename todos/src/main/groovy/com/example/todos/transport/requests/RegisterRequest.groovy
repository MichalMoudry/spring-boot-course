package com.example.todos.transport.requests

import com.example.todos.service.model.UserRegisterData
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@CompileStatic
final class RegisterRequest {
    @NotEmpty(message = 'First name is mandatory')
    @Size(min = 3, max = 30, message = 'First name must be at least 3 character long')
    @JsonProperty('first_name')
    String firstName

    @NotEmpty(message = 'Last name is mandatory')
    @Size(min = 3, max = 30, message = 'Last name must be at least 3 character long')
    @JsonProperty('last_name')
    String lastName

    @NotEmpty(message = 'Email is mandatory')
    @Email(message = 'Invalid email format')
    @JsonProperty('email')
    String email

    @NotEmpty(message = 'Password is mandatory')
    @Size(min = 7, max = 256, message = 'Password must be at least 7 character long')
    @JsonProperty('password')
    String password

    RegisterRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }

    UserRegisterData toServiceModel() {
        new UserRegisterData(
                firstName,
                lastName,
                email,
                password
        )
    }
}
