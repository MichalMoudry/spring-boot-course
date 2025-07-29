package com.example.demo.transport

import groovy.transform.CompileStatic
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
@CompileStatic
class LoginController {
    @GetMapping('/showMyLoginPage')
    String showMyLoginPage() {
        'login-page'
    }

    @GetMapping('/access-denied')
    String showAccessDeniedPage() {
        ResponseEntity.status(HttpStatusCode.valueOf(403))
    }
}
