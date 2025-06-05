package com.example.cruddemo.database.dao

import com.example.cruddemo.database.model.Student
import groovy.transform.CompileStatic

@CompileStatic
interface IStudentDao {
    void save(Student student)
    Student find(long id)
}