package com.example.demo.entity

import groovy.transform.CompileStatic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = 'review', schema = 'hb_03_one_to_many')
@CompileStatic
class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private long id

    @Column(name = 'comment')
    private String comment

    Review() { }

    Review(String comment) {
        this.comment = comment
    }

    long getId() {
        id
    }

    void setId(long id) {
        this.id = id
    }

    String getComment() {
        comment
    }

    void setComment(String comment) {
        this.comment = comment
    }

    @Override
    String toString() {
        "Review{id=$id, comment='$comment'}"
    }
}
