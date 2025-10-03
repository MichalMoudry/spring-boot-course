package com.example.todos.service

import com.example.todos.service.model.AuthData
import com.example.todos.service.model.AuthResult
import com.example.todos.service.model.UserRegisterData
import groovy.transform.CompileStatic

@CompileStatic
interface IAuthService {
    void register(UserRegisterData data) throws Exception
    AuthResult login(AuthData requestData)
}