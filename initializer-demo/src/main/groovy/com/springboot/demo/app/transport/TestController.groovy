package com.springboot.demo.app.transport

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
final class TestController {
    @GetMapping('/')
    String sayHello() {
        "Hello, world!"
    }
}
