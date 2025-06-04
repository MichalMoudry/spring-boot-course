package com.springboot.demo.app.transport

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
final class TestController {
    @Value('${az.keyvault.connection}')
    String keyVaultConnStr

    @GetMapping('/')
    String sayHello() {
        "Hello, world!"
    }

    @GetMapping('/msg')
    String sayMessage() {
        "New message!"
    }

    @GetMapping('/amount')
    float getAmount() {
        5.3
    }

    @GetMapping('/kv')
    String test() {
        keyVaultConnStr
    }
}
