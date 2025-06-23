package com.example.demo.database

import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.JpaRepository

@CompileStatic
interface IEmployeeRepository extends JpaRepository<Employee, UUID> {
}