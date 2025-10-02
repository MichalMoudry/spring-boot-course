package com.example.todos.service.model

import groovy.transform.CompileStatic

@CompileStatic
record UserRegisterData(
        String firstName,
        String lastName,
        String email,
        String password
) {}
