package com.example.employees.database

import com.example.employees.database.entity.Employee
import groovy.transform.CompileStatic
import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
class EmployeeRepository implements IEmployeeRepository {
    private final EntityManager entityManager

    EmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager
    }

    @Override
    List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery(
                'from Employee',
                Employee
        )
        query.getResultList()
    }

    @Override
    Optional<Employee> findById(long id) {
        Employee employee = entityManager.find(Employee, id)
        if (employee == null) {
            return Optional.empty()
        }
        else {
            return Optional.of(employee)
        }
    }

    @Override
    Employee save(Employee employee) {
        Employee result = entityManager.merge(employee)
        result
    }

    @Override
    void deleteById(long id) {
        Optional<Employee> employee = findById(id)
        if (employee.isPresent()) {
            entityManager.remove(employee.get())
        }
    }
}
