package com.example.employees.service

import com.example.employees.database.IEmployeeRepository
import com.example.employees.database.entity.Employee
import com.example.employees.service.model.EmployeeData
import groovy.transform.CompileStatic
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@CompileStatic
class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository

    EmployeeService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository
    }

    @Override
    List<Employee> fetchAllEntities() { employeeRepository.findAll() }

    @Override
    Employee findById(long id) { employeeRepository.findById(id).orElse(null) }

    @Transactional
    @Override
    Employee save(EmployeeData employee) {
        Employee newEmployee = convertToEntity(0, employee)
        employeeRepository.save(newEmployee)
    }

    @Transactional
    @Override
    Employee update(long id, EmployeeData employee) {
        Employee newEmployee = convertToEntity(id, employee)
        employeeRepository.save(newEmployee)
    }

    @Transactional
    @Override
    void deleteById(long id) { employeeRepository.deleteById(id) }

    private static Employee convertToEntity(long id, EmployeeData data) {
        new Employee(id, data.firstName(), data.lastName(), data.email())
    }
}
