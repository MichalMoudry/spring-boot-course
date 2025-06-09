package com.example.cruddemo.database.dao

import com.example.cruddemo.database.model.Student
import groovy.transform.CompileStatic
import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
class StudentDao implements IStudentDao {
    private EntityManager entityManager

    @Autowired
    StudentDao(EntityManager entityManager) {
        this.entityManager = entityManager
    }

    @Override
    @Transactional
    void save(Student student) {
        entityManager.persist(student)
    }

    @Override
    Student find(long id) {
        entityManager.find(Student.class, id)
    }

    @Override
    List<Student> findAllEntities() {
        TypedQuery<Student> query = entityManager.createQuery(
                "FROM Student ORDER BY firstName DESC",
                Student.class
        )
        query.getResultList()
    }

    @Override
    List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery(
                "FROM Student WHERE lastName=:lastName",
                Student.class
        )
        query.setParameter('lastName', lastName)
        query.getResultList()
    }

    @Override
    @Transactional
    void update(Student student) {
        entityManager.merge(student)
    }

    @Override
    @Transactional
    void delete(long id) {
        Student student = entityManager.find(Student.class, id)
        entityManager.remove(student)
    }
}
