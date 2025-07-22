package com.example.demo.entity

import groovy.transform.CompileStatic
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = 'student', schema = 'hb_03_one_to_many')
@CompileStatic
class Student {
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    ], mappedBy = 'students')
    private List<Course> courses

    Student() { }

    Student(String firstName, String lastName, String email) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
    }

    long getId() {
        id
    }

    void setId(long id) {
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

    List<Course> getCourses() {
        return courses
    }

    void setCourses(List<Course> courses) {
        this.courses = courses
    }

    void addCourse(Course course) {
        if (courses == null) {
            courses = []
        }
        courses.add(course)
        course.addStudent(this)
    }

    void addCourse(Course... courseList) {
        if (courses == null) {
            courses = []
        }
        for (Course course in courseList) {
            addCourse(course)
        }
    }

    @Override
    String toString() {
        "Student{id=$id, full_name='$firstName $lastName', email='$email'}"
    }
}
