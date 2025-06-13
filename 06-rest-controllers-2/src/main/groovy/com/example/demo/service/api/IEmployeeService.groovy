package com.example.demo.service.api

import com.example.demo.database.model.Employee

interface IEmployeeService {
    List<Employee> getAll()
    Employee getSingle(UUID id)
    Employee save(Employee employee)
    void delete(UUID id)
}