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
		(runner) -> deleteStudent(studentDao)
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
		println(student != null ? student.toString() : 'No student found')
	}

	static void getAllStudents(StudentDao dao) {
		println('Fetching all students')
		List<Student> students = dao.findAllEntities()
		println("Got ${students.size()} entities")
		for (def student in students) {
			println student
		}
	}

	static void findByLastName(StudentDao dao, String lastName) {
		println("Fetching students with a last name of '$lastName'")
		if (lastName != null || lastName.size() == 0) {
			println('Empty last name')
			return
		}
		List<Student> students = dao.findByLastName(lastName)
		println("Got ${students.size()} entities")
		for (Student student in students) {
			println student
		}
	}

	static void updateStudent(StudentDao dao) {
		int studentId = 1
		println("Getting a student with an id of $studentId")
		Student student = dao.find(studentId)
		println("Retrieved the following student: $student")

		student.setFirstName('Scooby')
		dao.update(student)

		println("Current version of the student entity: $student")
	}

	static void deleteStudent(StudentDao dao) {
		dao.delete(10)
		println('Entity removed')
	}
}
