package com.example.todos.transport

import com.example.todos.service.IAuthService
import com.example.todos.service.model.AuthResult
import com.example.todos.transport.requests.AuthRequest
import com.example.todos.transport.requests.RegisterRequest
import com.example.todos.transport.responses.AuthResponse
import groovy.transform.CompileStatic
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/api/auth')
@Tag(
        name = 'Authentication REST endpoints',
        description = 'Operations related to register & login')
@CompileStatic
class AuthController {
    private final IAuthService authService

    AuthController(IAuthService authService) {
        this.authService = authService
    }

    @Operation(
            summary = 'Register a user',
            description = 'Create a new user in the database')
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping('/register')
    void register(@Valid @RequestBody RegisterRequest request) throws Exception {
        authService.register(request.toServiceModel())
    }

    @Operation(
            summary = 'Login a user',
            description = 'Submit email & password to authenticate user')
    @ResponseStatus(HttpStatus.OK)
    @PostMapping('/login')
    AuthResponse login(@Valid @RequestBody AuthRequest request) {
        AuthResult result = authService.login(request.toServiceModel())
        new AuthResponse(result.token())
    }
}
