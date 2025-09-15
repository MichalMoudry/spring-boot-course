package com.example.books.transport

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CompileStatic
class HealthController {
    @GetMapping('/health')
    String health() { 'Healthy' }
}
