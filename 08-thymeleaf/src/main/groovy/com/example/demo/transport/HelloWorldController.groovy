package com.example.demo.transport

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@CompileStatic
final class HelloWorldController {
    @GetMapping('/showForm')
    String showForm() {
        'helloworld-form'
    }

    @PostMapping('/processForm')
    String processForm(
            @RequestParam('studentName') String studentName,
            Model model) {
        String message = studentName.toUpperCase()
        model.addAttribute('message', message)
        'helloworld'
    }
}
