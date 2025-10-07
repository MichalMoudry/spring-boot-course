package com.example.todos.transport

import com.example.todos.service.IUserService
import com.example.todos.transport.requests.PasswordUpdateRequest
import com.example.todos.transport.responses.UserInfoResponse
import groovy.transform.CompileStatic
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/api/users')
@Tag(
        name = 'User REST API endpoints',
        description = 'Operations related to info about current user')
@CompileStatic
class UserController {
    private final IUserService userService

    UserController(IUserService userService) {
        this.userService = userService
    }

    @Operation(summary = 'User information', description = 'Get current user info')
    @ResponseStatus(HttpStatus.OK)
    @GetMapping('/info')
    UserInfoResponse getUserInfo() {
        new UserInfoResponse(userService.getUserInfo())
    }

    @Operation(summary = 'Delete user', description = 'Delete current user account')
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    void deleteUser() {
        userService.deleteUser()
    }

    @Operation(
            summary = 'Password update',
            description = 'Change user password after verification')
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping('/password')
    void updatePassword(@Valid @RequestBody PasswordUpdateRequest request)
            throws Exception {
        userService.updatePassword(request.toServiceModel())
    }
}
