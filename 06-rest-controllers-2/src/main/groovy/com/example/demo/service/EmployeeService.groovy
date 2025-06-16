package com.example.demo.service

import com.example.demo.database.api.IEmployeeDao
import com.example.demo.database.model.Employee
import com.example.demo.service.api.IEmployeeService
import com.example.demo.service.model.EmployeeDto
import groovy.transform.CompileStatic
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@CompileStatic
class EmployeeService implements IEmployeeService {
    private IEmployeeDao dao

    EmployeeService(IEmployeeDao employeeDao) {
        this.dao = employeeDao
    }

    @Override
    List<Employee> getAll() {
        dao.all
    }

    @Override
    Employee getSingle(UUID id) {
        dao.getOne(id)
    }

    @Override
    @Transactional
    EmployeeDto save(EmployeeDto employee) {
        Employee result = dao.save(
                new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail()
                )
        )
        employee.setId(result.getId())
        employee
    }

    @Override
    @Transactional
    void delete(UUID id) {
        dao.deleteById(id)
    }
}
