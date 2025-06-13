package com.example.demo.database.model

import groovy.transform.CompileStatic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = 'employees')
@CompileStatic
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = 'id')
    private UUID id

    @Column(name = 'first_name')
    private String firstName

    @Column(name = 'last_name')
    private String lastName

    @Column(name = 'email')
    private String email

    Employee() {}

    Employee(String firstName, String lastName, String email) {
        //this.id = UUID.randomUUID()
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }

    UUID getId() {
        id
    }

    void setId(UUID id) {
        this.id = id
    }

    String getFirstName() {
        firstName
    }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() {
        lastName
    }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getEmail() {
        email
    }

    void setEmail(String email) {
        this.email = email
    }

    @Override
    String toString() {
        "Employee{id=$id, firstName=$firstName, lastName='$lastName', email='$email'}"
    }
}
