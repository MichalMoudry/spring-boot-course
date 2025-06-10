package com.example.demo.transport

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
@RequestMapping('/demo')
final class DemoController {
    @GetMapping('health')
    String health() {
        'Healthy'
    }
}
