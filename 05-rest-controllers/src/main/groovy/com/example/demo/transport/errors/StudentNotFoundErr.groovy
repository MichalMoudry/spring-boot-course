package com.example.demo.transport.errors

import groovy.transform.CompileStatic

@CompileStatic
final class StudentNotFoundErr extends RuntimeException {
    StudentNotFoundErr(String message) {
        super(message)
    }

    StudentNotFoundErr(String message, Throwable cause) {
        super(message, cause)
    }

    StudentNotFoundErr(Throwable cause) {
        super(cause)
    }
}
