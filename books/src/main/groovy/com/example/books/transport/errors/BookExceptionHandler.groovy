package com.example.books.transport.errors

import groovy.transform.CompileStatic
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import java.time.Instant

@ControllerAdvice
@CompileStatic
class BookExceptionHandler {
    @ExceptionHandler
    ResponseEntity<BookErrorResponse> handleError(BookNotFoundErr err) {
        BookErrorResponse response = new BookErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                err.message,
                Instant.now()
        )
        new ResponseEntity<BookErrorResponse>(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    ResponseEntity<BookErrorResponse> handleError(Exception err) {
        BookErrorResponse response = new BookErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                'Invalid request',
                Instant.now()
        )
        new ResponseEntity<BookErrorResponse>(response, HttpStatus.BAD_REQUEST)
    }
}
