package com.caching.caching.service;

import java.util.List;
import java.util.Optional;

import com.caching.caching.entity.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Employee updateEmployee(Employee updatedEmployee);
    void deleteEmployee(long id);
}
