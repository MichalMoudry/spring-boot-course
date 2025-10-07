package com.example.todos.errors

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

import java.time.Instant

@CompileStatic
final class ErrResponse {
    @JsonProperty('status')
    int status
    @JsonProperty('msg')
    String message
    @JsonProperty('timestamp')
    Instant timeStamp
}
