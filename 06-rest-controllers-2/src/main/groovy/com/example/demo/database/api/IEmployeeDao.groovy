package com.example.demo.database.api

import com.example.demo.database.model.Employee
import groovy.transform.CompileStatic

@CompileStatic
interface IEmployeeDao {
    List<Employee> getAll()
    Employee getOne(UUID id)
    Employee save(Employee employee)
    void deleteById(UUID id)
}