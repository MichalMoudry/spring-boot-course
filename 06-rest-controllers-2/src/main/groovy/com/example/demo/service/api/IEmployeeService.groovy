package com.example.demo.service.api

import com.example.demo.database.model.Employee
import com.example.demo.service.model.EmployeeDto

interface IEmployeeService {
    List<Employee> getAll()
    Employee getSingle(UUID id)
    EmployeeDto save(EmployeeDto employee)
    void delete(UUID id)
}