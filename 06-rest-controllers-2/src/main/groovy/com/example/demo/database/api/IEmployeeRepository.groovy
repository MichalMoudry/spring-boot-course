package com.example.demo.database.api

import com.example.demo.database.model.Employee
import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository

@CompileStatic
interface IEmployeeRepository extends JpaRepository<Employee, UUID> { }