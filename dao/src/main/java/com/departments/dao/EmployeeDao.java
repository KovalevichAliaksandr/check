package com.departments.dao;

import com.departments.model.Employee;

import java.util.List;

/**
 * Created by alex on 7.2.17.
 */
public interface EmployeeDao {

     Employee findEmployeeById(Long id);
     List<Employee> findAllEmployees();
     Long save(Employee Employee);
     void delete(Long id);
     void update(Employee Employee);
}
