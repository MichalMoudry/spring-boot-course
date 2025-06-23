package com.example.demo.transport

import com.example.demo.database.Employee
import com.example.demo.service.IEmployeeService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CompileStatic
@RestController
@RequestMapping('/api/employees')
final class EmployeeController {
    private IEmployeeService employeeService
    private ObjectMapper objMapper

    @Autowired
    EmployeeController(IEmployeeService employeeService, ObjectMapper objMapper) {
        this.employeeService = employeeService
        this.objMapper = objMapper
    }

    @GetMapping("/")
    List<Employee> findAll() {
        employeeService.findAll()
    }

    @GetMapping("/{employeeId}")
    Employee getEmployee(@PathVariable UUID employeeId) {
        Optional<Employee> theEmployee = employeeService.findById(employeeId)
        if (theEmployee.isEmpty()) {
            throw new RuntimeException("Employee id not found - ${employeeId}")
        }

        theEmployee.get()
    }

    @PostMapping()
    Employee addEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee)
    }

    @PutMapping()
    Employee updateEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee)
    }

    @PatchMapping("/{employeeId}")
    Employee patchEmployee(@PathVariable UUID employeeId,
                           @RequestBody Map<String, Object> patchPayload) {
        Optional<Employee> tempEmployee = employeeService.findById(employeeId)
        if (tempEmployee.isEmpty()) {
            throw new RuntimeException("Employee id not found - ${employeeId}")
        }

        if (patchPayload.containsKey("id")) {
            throw new RuntimeException(
                    "Employee id not allowed in request body - ${employeeId}"
            )
        }

        Employee patchedEmployee = apply(patchPayload, tempEmployee.get())
        Employee dbEmployee = employeeService.save(patchedEmployee)

        dbEmployee
    }

    private Employee apply(Map<String, Object> patchPayload, Employee tempEmployee) {
        // Convert employee object to a JSON object node
        ObjectNode employeeNode = objMapper.convertValue(
                tempEmployee,
                ObjectNode.class
        )

        // Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objMapper.convertValue(
                patchPayload,
                ObjectNode.class
        )

        // Merge the patch updates into the employee node
        employeeNode.setAll(patchNode)
        objMapper.convertValue(employeeNode, Employee.class)
    }

    @DeleteMapping("/{employeeId}")
    String deleteEmployee(@PathVariable UUID employeeId) {
        Optional<Employee> tempEmployee = employeeService.findById(employeeId)
        if (tempEmployee.isEmpty()) {
            throw new RuntimeException("Employee id not found - ${employeeId}")
        }

        employeeService.deleteById(employeeId)
        "Deleted employee id - ${employeeId}"
    }
}
