package com.example.ems.repository;

import com.example.ems.model.Employee;
import java.util.List;

public interface EmployeeRepository {
    void save(Employee employee);
    List<Employee> findAll();
}
