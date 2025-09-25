package com.example.employees.database.entity

import groovy.transform.CompileStatic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = 'employee')
@CompileStatic
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private long id

    @Column(name = 'first_name')
    private String firstName

    @Column(name = 'last_name')
    private String lastName

    @Column(name = 'email')
    private String email

    Employee() { }

    Employee(long id, String firstName, String lastName, String email) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }

    long getId() { id }

    void setId(long id) {
        this.id = id
    }

    String getFirstName() { firstName }

    void setFirstName(String firstName) {
        this.firstName = firstName
    }

    String getLastName() { lastName }

    void setLastName(String lastName) {
        this.lastName = lastName
    }

    String getEmail() { email }

    void setEmail(String email) {
        this.email = email
    }

    @Override
    String toString() {
        "Employee{id=$id, firstName='$firstName', lastName='$lastName', email='$email'}"
    }
}
