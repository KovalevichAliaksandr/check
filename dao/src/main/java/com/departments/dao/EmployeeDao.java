package com.departments.dao;

import com.departments.model.Employee;
import com.departments.model.EmployeeWithDepartment;

import java.util.Date;
import java.util.List;

/**
 * Created by alex on 7.2.17.
 */
public interface EmployeeDao {

     Employee findEmployeeById(Long id);
     EmployeeWithDepartment findEmployeeWithDepartmentById(Long id);
     public List<EmployeeWithDepartment> findEmployeeWithFilter(Date startDate, Date endDate);
     List<Employee> findAllEmployees();
     List<EmployeeWithDepartment> findAllEmployeesWithDepartments();
     Long save(Employee Employee);
     void delete(Long id);
     void update(Employee Employee);

}
