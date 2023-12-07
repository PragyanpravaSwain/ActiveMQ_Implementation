package com.abc.api.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.api.entity.Employee;
import com.abc.api.message.EmployeeMessageProducer;
import com.abc.api.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMessageProducer messageProducer;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        // Additional logic before saving the employee, if needed
        validateEmployee(employee);

        Employee createdEmployee = employeeRepository.save(employee);

        messageProducer.sendMessage("employee.queue.create",createdEmployee);

        return createdEmployee;
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        // Additional logic before updating the employee, if needed
        validateEmployee(updatedEmployee);

        // Implement update logic
        Employee savedEmployee = employeeRepository.save(updatedEmployee);

        messageProducer.sendMessage("employee.queue.update",savedEmployee);

        return savedEmployee;
    }

    public void deleteEmployee(Long id) {
        // Additional logic before deleting the employee, if needed

        employeeRepository.deleteById(id);

    }

    private void validateEmployee(Employee employee) {
        // Implement validation logic, for example, check if the employee details are valid
        if ( employee.getName().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty");
        }
        // Add more validation as needed
    }
}
