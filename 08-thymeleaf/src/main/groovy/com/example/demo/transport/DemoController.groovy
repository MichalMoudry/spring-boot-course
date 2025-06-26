package com.example.demo.transport

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

import java.time.Instant

@Controller
@CompileStatic
final class DemoController {
    @GetMapping('/hello')
    String sayHello(Model model) {
        model.addAttribute('date', Instant.now())
        'helloworld'
    }
}
