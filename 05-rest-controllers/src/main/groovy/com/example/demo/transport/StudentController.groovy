package com.example.demo.transport

import com.example.demo.transport.errors.StudentNotFoundErr
import com.example.demo.transport.model.Student
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
@RequestMapping('/students')
final class StudentController {
    final List<Student> students = [
            new Student('Test', 'name 1'),
            new Student('Test', 'name 2'),
            new Student('Test', 'name 3')
    ]

    @GetMapping
    List<Student> getStudents() {
        students
    }

    @GetMapping('/{id}')
    Optional<Student> getStudent(@PathVariable('id') int id) {
        if (id >= students.size() || id < 0) {
            throw new StudentNotFoundErr('student not found')
        }
        Optional<Student>.of(students.get(id))
    }
}
