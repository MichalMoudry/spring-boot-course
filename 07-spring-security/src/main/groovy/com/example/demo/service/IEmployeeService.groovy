package com.example.demo.service

import com.example.demo.database.Employee
import groovy.transform.CompileStatic

@CompileStatic
interface IEmployeeService {
    List<Employee> findAll()
    Optional<Employee> findById(UUID id)
    Employee save(Employee employee)
    void deleteById(UUID id)
}