package com.example.demo.entity

import groovy.transform.CompileStatic
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = 'instructor_detail', schema = 'hb_03_one_to_many')
@CompileStatic
class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = 'id')
    private long id

    @Column(name = 'youtube_channel')
    private String youtubeChannel

    @Column(name = 'hobby')
    private String hobby

    @OneToOne(mappedBy = 'detail', cascade = CascadeType.ALL)
    private Instructor instructor

    InstructorDetail() {}

    InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel
        this.hobby = hobby
    }

    int getId() {
        id
    }

    void setId(int id) {
        this.id = id
    }

    String getYoutubeChannel() {
        youtubeChannel
    }

    void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel
    }

    String getHobby() {
        hobby
    }

    void setHobby(String hobby) {
        this.hobby = hobby
    }

    Instructor getInstructor() {
        instructor
    }

    void setInstructor(Instructor instructor) {
        this.instructor = instructor
    }

    @Override
    String toString() {
        "InstructorDetail{id=$id, channel='$youtubeChannel', hobby='$hobby'}"
    }
}
