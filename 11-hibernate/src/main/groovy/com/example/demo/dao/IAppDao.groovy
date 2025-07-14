package com.example.demo.dao

import com.example.demo.entity.Instructor
import com.example.demo.entity.InstructorDetail
import groovy.transform.CompileStatic

@CompileStatic
interface IAppDao {
    void save(Instructor instructor)

    Instructor findInstructorById(int id)

    void deleteInstructor(int id)

    InstructorDetail findDetailById(int id)
}