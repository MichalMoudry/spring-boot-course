package com.example.books.transport.errors

import groovy.transform.CompileStatic

@CompileStatic
final class BookNotFoundErr extends RuntimeException {
    BookNotFoundErr(String message) {
        super(message)
    }

    BookNotFoundErr(String message, Throwable cause) {
        super(message, cause)
    }

    BookNotFoundErr(Throwable cause) {
        super(cause)
    }
}
