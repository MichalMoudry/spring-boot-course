package com.example.books.transport.errors

import groovy.transform.CompileStatic

import java.time.Instant

@CompileStatic
final class BookErrorResponse {
    int status
    String message
    Instant timeStamp

    BookErrorResponse(int status, String message, Instant timeStamp) {
        this.status = status
        this.message = message
        this.timeStamp = timeStamp
    }
}
