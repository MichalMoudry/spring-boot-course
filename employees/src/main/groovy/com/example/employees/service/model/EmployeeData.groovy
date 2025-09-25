package com.example.employees.service.model

import groovy.transform.CompileStatic

@CompileStatic
record EmployeeData(String firstName, String lastName, String email) { }
