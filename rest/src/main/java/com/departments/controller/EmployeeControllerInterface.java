package com.departments.controller;

import com.departments.model.Employee;
import com.departments.model.EmployeeWithDepartment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alex on 6.3.17.
 */
public interface EmployeeControllerInterface {

    List<Employee> listData();
    List<EmployeeWithDepartment> listDataWithDepartments();
    List<EmployeeWithDepartment> listEmployeesWithFilter(String startDate,String endDate);
    EmployeeWithDepartment findEmployeeWithDepartmentById( Long id);
    Employee findEmployeeById( Long id);
    Long create( Employee employee);
    Employee update( Employee employee, Long id);
    void delete(Long id);
}
