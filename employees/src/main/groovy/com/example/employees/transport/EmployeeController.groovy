package com.example.employees.transport

import com.example.employees.database.entity.Employee
import com.example.employees.service.IEmployeeService
import com.example.employees.transport.model.EmployeeRequest
import groovy.transform.CompileStatic
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/api/employees')
@Tag(
        name = 'Employee REST API endpoints',
        description = 'Operations related to employees')
@CompileStatic
class EmployeeController {
    private final IEmployeeService employeeService

    EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService
    }

    @GetMapping
    @Operation(
            summary = 'Get all employees',
            description = 'Retrieve a list of all employees')
    @ResponseStatus(HttpStatus.OK)
    List<Employee> getAllEmployees() {
        employeeService.fetchAllEntities()
    }

    @GetMapping('/{id}')
    @Operation(
            summary = 'Get an employee',
            description = 'Retrieve a single employee by id')
    @ResponseStatus(HttpStatus.OK)
    Employee getSingleEmployee(@PathVariable(name = 'id') long id) {
        employeeService.findById(id)
    }

    @PostMapping
    @Operation(
            summary = 'Adds an employee',
            description = 'Add a new employee to database')
    @ResponseStatus(HttpStatus.CREATED)
    Employee addNewEmployee(@Valid @RequestBody EmployeeRequest request) {
        employeeService.save(request.toServiceObject())
    }

    @PutMapping('/{id}')
    @Operation(
            summary = 'Update an employee',
            description = 'Update an existing employee in the database')
    @ResponseStatus(HttpStatus.OK)
    Employee updateEmployee(
            @PathVariable(name = 'id') long id,
            @Valid @RequestBody EmployeeRequest request) {
        employeeService.update(id, request.toServiceObject())
    }

    @DeleteMapping('/{id}')
    @Operation(
            summary = 'Delete a user',
            description = 'Remove an existing employee from the database')
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmployee(@PathVariable(name = 'id') long id) {
        employeeService.deleteById(id)
    }
}
