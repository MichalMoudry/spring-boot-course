package com.example.todos.service.util

import com.example.todos.database.entity.User
import groovy.transform.CompileStatic

@CompileStatic
interface IFindAuthenticatedUser {
    User getAuthenticatedUser()
}