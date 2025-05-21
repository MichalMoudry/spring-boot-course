package com.springboot.demo.app

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@CompileStatic
@SpringBootApplication
class AppApplication {
	static void main(String[] args) {
		SpringApplication.run(AppApplication, args)
	}
}
