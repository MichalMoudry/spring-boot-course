package com.example.demo.model

import groovy.transform.CompileStatic

@CompileStatic
record CourseInfo(String title, String instructor, int numberOfStudents) { }