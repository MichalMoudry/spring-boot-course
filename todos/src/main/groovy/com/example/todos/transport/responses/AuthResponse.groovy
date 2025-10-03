package com.example.todos.transport.responses

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

@CompileStatic
record AuthResponse(@JsonProperty('token') String token) {}
