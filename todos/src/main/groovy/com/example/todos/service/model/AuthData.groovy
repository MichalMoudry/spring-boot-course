package com.example.todos.service.model

import groovy.transform.CompileStatic

@CompileStatic
record AuthData(String email, String password) {}
