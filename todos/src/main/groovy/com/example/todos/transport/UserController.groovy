package com.example.todos.transport

import com.example.todos.database.entity.User
import com.example.todos.service.IUserService
import com.example.todos.transport.responses.UserInfoResponse
import groovy.transform.CompileStatic
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
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

    @GetMapping('/info')
    UserInfoResponse getUserInfo() {
        new UserInfoResponse(userService.getUserInfo())
    }
}
