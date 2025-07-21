package com.example.demo

import com.example.demo.dao.IAppDao
import com.example.demo.entity.Course
import com.example.demo.entity.Instructor
import com.example.demo.entity.InstructorDetail
import com.example.demo.entity.Review
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
			// createInstructorWithCourses(appDao)
			// findInstructorWithCourses(appDao)

			/*findInstructorWithJoinFetch(appDao)
			updateInstructor(appDao)
			findInstructorWithJoinFetch(appDao)*/

			// updateCourse(appDao)

			// addCourseAndReviews(appDao)

			findCourseAndReviews(appDao)
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

	static void createInstructorWithCourses(IAppDao appDao) {
		Instructor tempInstructor = new Instructor(
				'Susan',
				'Darby',
				'chad@darby.com'
		)
		InstructorDetail detail = new InstructorDetail(
				'https://test.com',
				'Sport'
		)
		tempInstructor.setDetail(detail)

		Course[] courses = [
		        new Course('Air Guitar'),
				new Course('Pinball')
		]
		for (Course course in courses) {
			println("Will save $course course")
			tempInstructor.add(course)
		}

		println("Saving the instructor: $tempInstructor")
		appDao.save(tempInstructor)
	}

	static void findInstructorWithCourses(IAppDao appDao) {
		int id = 1
		println("Trying to find instructor with ID of $id")
		Instructor instructor = appDao.findInstructorById(id)
		println("Found $instructor")

		if (instructor != null) {
			List<Course> courses = appDao.findCoursesByInstructor(instructor.id)
			println('The instructor has the following courses:')
			for (Course course in courses) {
				println(course)
			}
		}
		/*if (instructor != null) {
			List<Course> courses = instructor.getCourses()
			println('The instructor has the following courses:')
			for (Course course in courses) {
				println(course)
			}
		}*/
	}

	static void findInstructorWithJoinFetch(IAppDao appDao) {
		int instructorId = 1
		println("Trying to find instructor with ID of $instructorId")
		Instructor instructor = appDao.findInstructorByIdJoinFetch(instructorId)
		println("Found $instructor")
		if (instructor != null) {
			List<Course> courses = appDao.findCoursesByInstructor(instructor.id)
			println('The instructor has the following courses:')
			for (Course course in courses) {
				println(course)
			}
		}
	}

	static void updateInstructor(IAppDao appDao) {
		int instructorId = 1
		Instructor instructor = appDao.findInstructorById(instructorId)
		instructor.lastName = 'TEST VAL'
		appDao.update(instructor)
	}

	static void updateCourse(IAppDao appDao) {
		int courseId = 1
		Course course = appDao.findCourseById(courseId)
		if (course != null) {
			println("Found course: $course")
			course.title = 'Hiking'
			appDao.update(course)
		}
	}

	static void deleteCourse(IAppDao appDao) {
		int courseId = 2
		appDao.deleteCourseById(courseId)
	}

	static void addCourseAndReviews(IAppDao appDao) {
		Course course = new Course('Pacman')
		course.addReview(new Review('Test review 1'))
		course.addReview(
				new Review('Test review 2'),
				new Review('Test review 3')
		)

		println('Saving the course')
		appDao.save(course)
	}

	static void findCourseAndReviews(IAppDao appDao) {
		int id = 4
		println("Trying to find a course with an ID of $id")

		Course course = appDao.findCourseWithReviews(id)
		if (course != null) {
			println("Found $course with the following reviews:")
			for (Review review in course.reviews) {
				println("\t- $review")
			}
		}
	}
}
