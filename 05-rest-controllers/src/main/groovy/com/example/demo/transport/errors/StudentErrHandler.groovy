package com.example.demo.transport.errors

import com.example.demo.transport.responses.StudentErrResponse
import groovy.transform.CompileStatic
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import java.time.Instant

@CompileStatic
@ControllerAdvice
final class StudentErrHandler {
    @ExceptionHandler
    ResponseEntity<StudentErrResponse> handleException(StudentNotFoundErr ex) {
        StudentErrResponse response = new StudentErrResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Instant.now()
        )
        new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    ResponseEntity<StudentErrResponse> handleException(Exception ex) {
        StudentErrResponse response = new StudentErrResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                Instant.now()
        )
        new ResponseEntity<>(response, HttpStatus.BAD_REQUEST)
    }
}
