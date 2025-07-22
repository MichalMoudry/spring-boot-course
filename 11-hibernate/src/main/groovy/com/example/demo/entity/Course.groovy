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
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = 'course', schema = 'hb_03_one_to_many')
@CompileStatic
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private long id

    @Column(name = 'title')
    private String title

    @ManyToOne(cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    ])
    @JoinColumn(name = 'instructor_id')
    private Instructor instructor

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = 'course_id', nullable = false)
    private List<Review> reviews

    @ManyToMany(fetch = FetchType.LAZY, cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    ])
    @JoinTable(
            name = 'course_student',
            schema = 'hb_03_one_to_many',
            joinColumns = @JoinColumn(name = 'course_id'),
            inverseJoinColumns = @JoinColumn(name = 'student_id')
    )
    private List<Student> students

    Course() { }

    Course(String title) {
        this.title = title
    }

    long getId() {
        id
    }

    void setId(long id) {
        this.id = id
    }

    String getTitle() {
        title
    }

    void setTitle(String title) {
        this.title = title
    }

    Instructor getInstructor() {
        instructor
    }

    void setInstructor(Instructor instructor) {
        this.instructor = instructor
    }

    List<Review> getReviews() {
        reviews
    }

    void setReviews(List<Review> reviews) {
        this.reviews = reviews
    }

    List<Student> getStudents() {
        return students
    }

    void setStudents(List<Student> students) {
        this.students = students
    }

    void addReview(Review review) {
        if (reviews == null) {
            reviews = []
        }
        reviews.add(review)
    }

    void addReview(Review... newReviews) {
        if (reviews == null) {
            reviews = []
        }
        reviews.addAll(newReviews)
    }

    void addStudent(Student student) {
        if (students == null) {
            students = []
        }
        students.add(student)
    }

    void addStudent(Student... newStudents) {
        if (students == null) {
            students = []
        }
        students.addAll(newStudents)
    }

    @Override
    String toString() {
        "Course{id=$id, title='$title'}"
    }
}
