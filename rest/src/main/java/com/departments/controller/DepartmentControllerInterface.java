package com.departments.controller;

import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alex on 6.3.17.
 */
public interface DepartmentControllerInterface {

    List<Department> listDepartments();
    List<DepartmentsWithAvgSalary> listDepartmentsWitAvgSalary();
    Department findContactById(Long id);
    Long create(Department department);
    Department update(Department department, Long id);
    void delete(Long id);
}
