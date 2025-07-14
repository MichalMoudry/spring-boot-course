package com.example.demo.entity

import groovy.transform.CompileStatic
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = 'instructor', schema = 'hb_01_one_to_one_uni')
@CompileStatic
class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private int id

    @Column(name = 'first_name')
    private String firstName

    @Column(name = 'last_name')
    private String lastName

    @Column(name = 'email')
    private String email

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = 'instructor_detail_id')
    private InstructorDetail detail

    Instructor() { }

    Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }

    int getId() { id }

    void setId(int id) {
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

    InstructorDetail getDetail() { detail }

    void setDetail(InstructorDetail detail) {
        this.detail = detail
    }

    @Override
    String toString() {
        "Instructor{id=$id, firstName='$firstName', lastName='$lastName', email='$email'}"
    }
}
