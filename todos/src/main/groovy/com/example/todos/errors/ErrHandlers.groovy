package com.example.todos.errors

import groovy.transform.CompileStatic
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

import java.time.Instant

@ControllerAdvice
@CompileStatic
class ErrHandlers {
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<ErrResponse> handleException(ResponseStatusException err) {
        buildResponseEntity(err, HttpStatus.valueOf(err.statusCode.value()))
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrResponse> handleException(Exception err) {
        buildResponseEntity(err, HttpStatus.BAD_REQUEST)
    }

    private static ResponseEntity<ErrResponse> buildResponseEntity(
            Exception err,
            HttpStatus status) {
        ErrResponse error = new ErrResponse()
        error.status = status.value()
        error.message = err.message
        error.timeStamp = Instant.now()

        new ResponseEntity<ErrResponse>(error, status)
    }
}
