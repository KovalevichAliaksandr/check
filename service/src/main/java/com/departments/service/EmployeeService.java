package com.departments.service;

import com.departments.model.Employee;

import java.util.List;

/**
 * Created by alex on 8.2.17.
 */
public interface EmployeeService {
    public Employee findEmployeeById(Long id);
    public List<Employee> findAllEmployees();
    public Long save(Employee employee);
    public void delete(Long id);
    public void update(Employee employee);
}




