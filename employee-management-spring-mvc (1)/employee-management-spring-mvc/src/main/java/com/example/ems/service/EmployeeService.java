package com.example.ems.service;

import com.example.ems.model.Employee;
import java.util.List;

public interface EmployeeService {
    void registerEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
