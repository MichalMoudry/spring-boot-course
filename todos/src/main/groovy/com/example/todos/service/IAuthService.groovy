package com.example.todos.service

import com.example.todos.service.model.UserRegisterData
import groovy.transform.CompileStatic

@CompileStatic
interface IAuthService {
    void register(UserRegisterData data) throws Exception
}