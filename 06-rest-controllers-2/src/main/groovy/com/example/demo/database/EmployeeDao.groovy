package com.example.demo.database

import com.example.demo.database.api.IEmployeeDao
import com.example.demo.database.model.Employee
import groovy.transform.CompileStatic
import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
class EmployeeDao implements IEmployeeDao {
    private EntityManager em

    @Autowired
    EmployeeDao(EntityManager entityManager) {
        this.em = entityManager
    }

    @Override
    List<Employee> getAll() {
        TypedQuery<Employee> query =
                em.createQuery('from Employee', Employee.class)
        query.getResultList()
    }

    @Override
    Employee getOne(UUID id) { em.find(Employee.class, id) }

    @Override
    Employee save(Employee employee) { em.merge(employee) }

    @Override
    void deleteById(UUID id) {
        Employee employee = getOne(id)
        em.remove(employee)
    }
}
