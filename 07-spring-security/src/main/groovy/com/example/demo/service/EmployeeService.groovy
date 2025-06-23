package com.example.demo.service

import com.example.demo.database.Employee
import com.example.demo.database.IEmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
final class EmployeeService implements IEmployeeService {
    private IEmployeeRepository employeeRepository

    @Autowired
    EmployeeService(IEmployeeRepository repository) {
        this.employeeRepository = repository
    }

    @Override
    List<Employee> findAll() {
        employeeRepository.findAll()
    }

    @Override
    Optional<Employee> findById(UUID id) {
        employeeRepository.findById(id)
    }

    @Override
    Employee save(Employee employee) {
        employeeRepository.save(employee)
    }

    @Override
    void deleteById(UUID id) {
        employeeRepository.deleteById(id)
    }
}
