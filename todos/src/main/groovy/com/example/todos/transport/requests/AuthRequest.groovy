package com.example.todos.transport.requests

import com.example.todos.service.model.AuthData
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@CompileStatic
final class AuthRequest {
    @NotEmpty(message = 'Email is mandatory')
    @Email(message = 'Invalid email format')
    @JsonProperty('email')
    String email

    @NotEmpty(message = 'Password is mandatory')
    @Size(min = 7, max = 256, message = 'Password must be at least 7 characters long')
    @JsonProperty('password')
    String password

    AuthData toServiceModel() { new AuthData(email, password) }
}
