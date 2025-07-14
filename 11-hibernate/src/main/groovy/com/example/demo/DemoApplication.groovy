package com.example.demo

import com.example.demo.dao.IAppDao
import com.example.demo.entity.Instructor
import com.example.demo.entity.InstructorDetail
import groovy.transform.CompileStatic
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@CompileStatic
@SpringBootApplication
class DemoApplication {

	static void main(String[] args) {
		SpringApplication.run(DemoApplication, args)
	}

	@Bean
	CommandLineRunner commandLineRunner(IAppDao appDao) {
		return { runner -> {
			println('Hello world!')
			// findInstructorDetail(appDao)
		} }
	}

	static void createInstructor(IAppDao dao) {
		Instructor tempInstructor = new Instructor(
				'Chad',
				'Darby',
				'chad@darby.com'
		)
		InstructorDetail detail = new InstructorDetail(
				'https://test.com',
				'Sport'
		)
		tempInstructor.setDetail(detail)

		println("Saving instructor: $tempInstructor")
		dao.save(tempInstructor)
		println("Done")
	}

	static void findInstructor(IAppDao dao) {
		Instructor instructor = dao.findInstructorById(1)
		println(instructor)
	}

	static void deleteInstructor(IAppDao dao) {
		dao.deleteInstructor(2)
		println(dao.findInstructorById(2))
	}

	static void findInstructorDetail(IAppDao dao) {
		InstructorDetail detail = dao.findDetailById(1)
		println(detail)
	}
}
