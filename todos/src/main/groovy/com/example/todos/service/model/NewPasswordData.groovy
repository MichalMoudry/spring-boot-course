package com.example.todos.service.model

import groovy.transform.CompileStatic

@CompileStatic
record NewPasswordData(
        String oldPassword,
        String newPassword,
        String passwordConfirmation
) { }