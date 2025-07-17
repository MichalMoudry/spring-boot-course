package com.example.demo.entity

import groovy.transform.CompileStatic
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = 'instructor', schema = 'hb_03_one_to_many')
@CompileStatic
class Instructor {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = 'instructor_detail_id')
    private InstructorDetail detail

    @OneToMany(mappedBy = 'instructor', cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    ], fetch = FetchType.LAZY)
    private List<Course> courses

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

    List<Course> getCourses() { courses }

    void setCourses(List<Course> courses) {
        this.courses = courses
    }

    void add(Course course) {
        if (courses == null) {
            courses = []
        }
        courses.add(course)
        course.setInstructor(this)
    }

    @Override
    String toString() {
        "Instructor{id=$id, firstName='$firstName', lastName='$lastName', email='$email'}"
    }
}
