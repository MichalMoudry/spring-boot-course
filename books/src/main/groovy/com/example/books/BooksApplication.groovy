package com.example.books

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@CompileStatic
class BooksApplication {

	static void main(String[] args) {
		SpringApplication.run(BooksApplication, args)
	}

}
