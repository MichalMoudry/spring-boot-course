package com.example.demo.transport.responses

import groovy.transform.CompileStatic

import java.time.Instant

@CompileStatic
final class StudentErrResponse {
    int status
    String message
    Instant timeStamp

    StudentErrResponse() {}

    StudentErrResponse(int status, String message, Instant timeStamp) {
        this.status = status
        this.message = message
        this.timeStamp = timeStamp
    }

    int getStatus() {
        return status
    }

    void setStatus(int status) {
        this.status = status
    }

    String getMessage() {
        return message
    }

    void setMessage(String message) {
        this.message = message
    }

    Instant getTimeStamp() {
        return timeStamp
    }

    void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp
    }
}
