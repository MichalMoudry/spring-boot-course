package com.example.demo.dao

import com.example.demo.entity.Instructor
import com.example.demo.entity.InstructorDetail
import groovy.transform.CompileStatic
import jakarta.persistence.EntityManager
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
        entityManager.remove(findInstructorById(id))
    }

    @Override
    InstructorDetail findDetailById(int id) {
        entityManager.find(InstructorDetail.class, id)
    }
}
