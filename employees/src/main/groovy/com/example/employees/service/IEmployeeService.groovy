package com.example.employees.service

import com.example.employees.database.entity.Employee
import com.example.employees.service.model.EmployeeData
import groovy.transform.CompileStatic

@CompileStatic
interface IEmployeeService {
    List<Employee> fetchAllEntities()
    Employee findById(long id)
    Employee save(EmployeeData employee)
    Employee update(long id, EmployeeData employee)
    void deleteById(long id)
}