package com.example.demo.transport

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
@CompileStatic
final class LoginController {
    @GetMapping('/showMyLoginPage')
    String showMyLoginPage() {
        'login-page'
    }

    @GetMapping('/access-denied')
    String showAccessDeniedPage() {
        '403'
    }
}
