package com.example.demo.transport

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
@CompileStatic
final class DemoController {
    @GetMapping('/')
    String showHome() {
        'home'
    }

    @GetMapping('/leaders')
    String showLeaders() {
        'leaders'
    }
}
