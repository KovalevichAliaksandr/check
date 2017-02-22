package com.departments.dao.service;

import com.departments.dao.model.DepartmentsWithAvgSalary;
import com.departments.dao.model.Department;

import java.util.List;

/**
 * Created by alex on 8.2.17.
 */

public interface DepartmentService {
     Department findDepartmentById(Long id);
     Long save(Department department);
     void delete(Long id);
     void update(Department department);
     List<Department> findAllDepartments();
     List<DepartmentsWithAvgSalary> findDepartmentsWithAvgSalary();

}
