package com.example.cruddemo

import groovy.transform.CompileStatic
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@CompileStatic
@SpringBootApplication
class CruddemoApplication {
	static void main(String[] args) {
		SpringApplication.run(CruddemoApplication, args)
	}

	@Bean
	CommandLineRunner cmdRunner(String[] args) {
		(runner) -> println('Hello world')
	}
}
