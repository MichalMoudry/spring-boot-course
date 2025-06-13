package com.example.demo.service

import com.example.demo.database.api.IEmployeeDao
import com.example.demo.database.model.Employee
import com.example.demo.service.api.IEmployeeService
import groovy.transform.CompileStatic
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@CompileStatic
class EmployeeService implements IEmployeeService {
    private IEmployeeDao dao

    EmployeeService(IEmployeeDao employeeDao) {
        this.dao = employeeDao
    }

    @Override
    List<Employee> getAll() {
        dao.all
    }

    @Override
    Employee getSingle(UUID id) {
        dao.getOne(id)
    }

    @Override
    @Transactional
    Employee save(Employee employee) {
        dao.save(employee)
    }

    @Override
    @Transactional
    void delete(UUID id) {
        dao.deleteById(id)
    }
}
