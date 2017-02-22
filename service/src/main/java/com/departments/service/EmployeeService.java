package com.departments.service;

import com.departments.model.Employee;

import java.util.List;

/**
 * Created by alex on 8.2.17.
 */
public interface EmployeeService {
     Employee findEmployeeById(Long id);
     List<Employee> findAllEmployees();
     Long save(Employee employee);
     void delete(Long id);
     void update(Employee employee);
}




