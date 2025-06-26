package com.example.demo.transport.model

import groovy.transform.CompileStatic

@CompileStatic
final class Student {
    private String firstName
    private String lastName

    Student() {}

    String getFirstName() { firstName }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() { lastName }

    void setLastName(String lastName) {
        this.lastName = lastName
    }
}
