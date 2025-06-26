package com.example.demo.transport

import com.example.demo.transport.model.Student
import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
@CompileStatic
final class StudentController {
    @GetMapping('/studentForm')
    String showForm(Model model) {
        Student student = new Student()
        model.addAttribute('student', student)
        'student-form'
    }

    @PostMapping('/processStudentForm')
    String processForm(@ModelAttribute('student') Student student) {
        println("student: ${student.firstName} ${student.lastName}")
        'student-confirmation'
    }
}
