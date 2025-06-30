package com.example.demo.transport.model

import groovy.transform.CompileStatic
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@CompileStatic
final class Customer {
    private String firstName

    @NotNull(message = 'is required')
    @Size(min = 1, message = 'is required')
    private String lastName

    String getFirstName() { firstName }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() { lastName }

    void setLastName(String lastName) {
        this.lastName = lastName
    }
}
