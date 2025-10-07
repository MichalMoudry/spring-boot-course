package com.example.todos.service

import com.example.todos.database.entity.User
import com.example.todos.service.model.NewPasswordData
import groovy.transform.CompileStatic

@CompileStatic
interface IUserService {
    User getUserInfo()
    void deleteUser()
    void updatePassword(NewPasswordData data)
}