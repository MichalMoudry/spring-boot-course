package com.example.demo.transport.model

import groovy.transform.CompileStatic

@CompileStatic
final class Student {
    String firstName
    String lastName

    Student() {}

    Student(String firstName, String lastName) {
        this.firstName = firstName
        this.lastName = lastName
    }
}
