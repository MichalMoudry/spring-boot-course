package com.example.demo.transport

import com.example.demo.database.model.Employee
import com.example.demo.service.api.IEmployeeService
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
@RequestMapping('/api/employees')
final class EmployeeController {
    private IEmployeeService employeeService

    EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService
    }

    @GetMapping('/')
    List<Employee> getAll(){
        employeeService.all
    }

    @GetMapping('/{id}')
    Employee getSingle(@PathVariable('id') UUID id) {
        employeeService.getSingle(id)
    }

    @DeleteMapping('/{id}')
    void delete(@PathVariable('id') UUID id) {
        employeeService.delete(id)
    }
}
