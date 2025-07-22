package com.example.demo.dao

import com.example.demo.entity.Course
import com.example.demo.entity.Instructor
import com.example.demo.entity.InstructorDetail
import com.example.demo.entity.Student
import groovy.transform.CompileStatic
import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
class AppDao implements IAppDao {
    private final EntityManager entityManager

    @Autowired
    AppDao(EntityManager entityManager) {
        this.entityManager = entityManager
    }

    @Override
    @Transactional
    void save(Instructor instructor) {
        entityManager.persist(instructor)
    }

    @Override
    Instructor findInstructorById(int id) {
        entityManager.find(Instructor.class, id)
    }

    @Override
    @Transactional
    void deleteInstructor(int id) {
        Instructor instructor = findInstructorById(id)
        List<Course> courses = instructor.getCourses()
        for (def course in courses) {
            course.instructor = null
        }
        entityManager.remove(instructor)
    }

    @Override
    InstructorDetail findDetailById(int id) {
        entityManager.find(InstructorDetail.class, id)
    }

    @Override
    List<Course> findCoursesByInstructor(int instructorId) {
        TypedQuery<Course> query = entityManager.createQuery(
                'from Course where instructor.id = :data',
                Course.class
        ).setParameter('data', instructorId)
        query.getResultList()
    }

    @Override
    Instructor findInstructorByIdJoinFetch(int instructorId) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                'select i from Instructor i JOIN FETCH i.courses where i.id = :data',
                Instructor.class
        ).setParameter('data', instructorId)
        query.getSingleResult()
    }

    @Override
    @Transactional
    void update(Instructor instructor) {
        entityManager.merge(instructor)
    }

    @Override
    @Transactional
    void update(Course course) {
        entityManager.merge(course)
    }

    @Override
    Course findCourseById(int id) {
        entityManager.find(Course.class, id)
    }

    @Override
    @Transactional
    void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id)
        entityManager.remove(course)
    }

    @Override
    @Transactional
    void save(Course course) {
        entityManager.persist(course)
    }

    @Override
    Course findCourseWithReviews(int courseId) {
        TypedQuery<Course> query = entityManager.createQuery(
                'select c from Course c JOIN FETCH c.reviews where c.id = :data',
                Course.class
        ).setParameter('data', courseId)

        query.getSingleResult()
    }

    @Override
    Course findCourseAndStudents(int courseId) {
        TypedQuery<Course> query = entityManager.createQuery(
                'select c from Course c JOIN FETCH c.students where c.id = :data',
                Course.class
        ).setParameter('data', courseId)
        query.getSingleResult()
    }

    @Override
    Student findStudent(int studentId) {
        TypedQuery<Student> query = entityManager.createQuery(
                'select s from Student s JOIN FETCH s.courses where s.id = :data ',
                Student.class
        ).setParameter('data', studentId)
        query.getSingleResult()
    }
}
