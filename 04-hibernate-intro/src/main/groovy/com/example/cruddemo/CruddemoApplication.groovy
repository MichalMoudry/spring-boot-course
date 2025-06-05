package com.example.cruddemo

import com.example.cruddemo.database.dao.StudentDao
import com.example.cruddemo.database.model.Student
import groovy.transform.CompileStatic
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

import java.util.stream.Stream

@CompileStatic
@SpringBootApplication
class CruddemoApplication {
	static void main(String[] args) {
		SpringApplication.run(CruddemoApplication, args)
	}

	@Bean
	CommandLineRunner cmdRunner(StudentDao studentDao) {
		(runner) -> findStudent(studentDao, 15)
	}

	static void createStudent(StudentDao studentDao) {
		println('Creating a new student object')
		Student student = new Student('Test', 'name', 'test2@test.com');
		studentDao.save(student);
		println("Generated ID: ${student.id}")
	}

	static void createMultipleStudents(StudentDao studentDao) {
		IntRange range = 11..15
		Stream<Student> students = range.toList().stream().map {
			i -> new Student("Test $i", 'name', "test$i@test.com")
		}
		for (Student student : students) {
			println(student)
			studentDao.save(student)
			println(student)
		}
	}

	static void findStudent(StudentDao dao, long id) {
		println("Trying to find student with an ID of $id")
		Student student = dao.find(id)
		println(student != null ? student.toString() : "No student found")
	}
}
