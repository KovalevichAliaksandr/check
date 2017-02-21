package com.departments.dao;

import com.departments.model.Employee;

import java.util.List;

/**
 * Created by alex on 7.2.17.
 */
public interface EmployeeDao {

    public Employee findEmployeeById(Long id);
    public List<Employee> findAllEmployees();
    public Long save(Employee Employee);
    public void delete(Long id);
    public void update(Employee Employee);
}
