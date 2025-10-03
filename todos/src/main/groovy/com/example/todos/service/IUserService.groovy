package com.example.todos.service

import com.example.todos.database.entity.User
import groovy.transform.CompileStatic

@CompileStatic
interface IUserService {
    User getUserInfo()
    void deleteUser()
}