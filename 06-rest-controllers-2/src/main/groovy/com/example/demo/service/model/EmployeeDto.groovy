package com.example.demo.service.model

import groovy.transform.CompileStatic

@CompileStatic
final class EmployeeDto {
    UUID id
    String firstName
    String lastName
    String email

    EmployeeDto(String firstName, String lastName, String email) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }
}
