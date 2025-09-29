package com.example.todos

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@CompileStatic
class TodosApplication {

	static void main(String[] args) {
		SpringApplication.run(TodosApplication, args)
	}

}
