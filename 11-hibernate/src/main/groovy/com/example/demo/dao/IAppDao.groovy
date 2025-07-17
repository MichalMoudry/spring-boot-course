package com.example.demo.dao

import com.example.demo.entity.Course
import com.example.demo.entity.Instructor
import com.example.demo.entity.InstructorDetail
import groovy.transform.CompileStatic

@CompileStatic
interface IAppDao {
    void save(Instructor instructor)

    Instructor findInstructorById(int id)

    void deleteInstructor(int id)

    InstructorDetail findDetailById(int id)

    List<Course> findCoursesByInstructor(int instructorId)

    Instructor findInstructorByIdJoinFetch(int instructorId)

    void update(Instructor instructor)

    void update(Course course)

    Course findCourseById(int id)

    void deleteCourseById(int id)
}