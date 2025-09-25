package com.example.employees.database

import com.example.employees.database.entity.Employee
import groovy.transform.CompileStatic

@CompileStatic
interface IEmployeeRepository {
    List<Employee> findAll()
    Optional<Employee> findById(long id)
    Employee save(Employee employee)
    void deleteById(long id)
}