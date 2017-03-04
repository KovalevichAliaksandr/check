package com.departments.service;

import com.departments.model.Employee;
import com.departments.model.EmployeeWithDepartment;

import java.util.Date;
import java.util.List;

/**
 * Created by alex on 8.2.17.
 */
public interface EmployeeService {
     Employee findEmployeeById(Long id);
     EmployeeWithDepartment findEmployeeWithDepartmentById(Long id);
     List<Employee> findAllEmployees();
     List<EmployeeWithDepartment> findAllEmployeesWithDepartments();
     List<EmployeeWithDepartment> findAllEmployeesWithFilter(Date startDate, Date endDate);
     Long save(Employee employee);
     void delete(Long id);
     void update(Employee employee);
}




