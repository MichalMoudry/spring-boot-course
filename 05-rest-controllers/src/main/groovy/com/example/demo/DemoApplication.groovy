package com.example.demo

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@CompileStatic
@SpringBootApplication
class DemoApplication {
	static void main(String[] args) {
		SpringApplication.run(DemoApplication, args)
	}
}
