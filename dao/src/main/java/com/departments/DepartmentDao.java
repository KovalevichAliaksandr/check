package com.departments;



import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;

import java.util.List;

/**
 * Created by alex on 8.2.17.
 */
public interface DepartmentDao {
     Department findDepartmentById(Long id);
     Long save(Department department);
     void delete(Long id);
     void update(Department department);
     List<Department> findAllDepartments();
     List<DepartmentsWithAvgSalary> findDepartmentsWithAvgSalary();
}