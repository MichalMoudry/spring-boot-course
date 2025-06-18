package com.example.demo.service

import com.example.demo.database.api.IEmployeeDao
import com.example.demo.database.api.IEmployeeRepository
import com.example.demo.database.model.Employee
import com.example.demo.service.api.IEmployeeService
import com.example.demo.service.model.EmployeeDto
import groovy.transform.CompileStatic
//import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@CompileStatic
class EmployeeService implements IEmployeeService {
    private IEmployeeDao dao
    private IEmployeeRepository repository

    EmployeeService(IEmployeeDao employeeDao) {
        this.dao = employeeDao
    }

    @Override
    List<Employee> getAll() {
        repository.findAll()
    }

    @Override
    Employee getSingle(UUID id) {
        Optional<Employee> result = repository.findById(id)
        if (result.isEmpty()) {
            return null
        }
        result.get()
    }

    @Override
    //@Transactional
    EmployeeDto save(EmployeeDto employee) {
        Employee result = repository.save(
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
    //@Transactional
    void delete(UUID id) {
        repository.deleteById(id)
    }
}
