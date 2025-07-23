package com.example.demo.dao

import com.example.demo.entity.Course
import com.example.demo.entity.Instructor
import com.example.demo.entity.InstructorDetail
import com.example.demo.entity.Student
import com.example.demo.model.CourseInfo
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

    void save(Course course)

    Course findCourseWithReviews(int courseId)

    Course findCourseAndStudents(int courseId)

    Student findStudent(int studentId)

    void update(Student student)

    CourseInfo getCourseInfo(int courseId)

    void deleteStudent(int studentId)
}