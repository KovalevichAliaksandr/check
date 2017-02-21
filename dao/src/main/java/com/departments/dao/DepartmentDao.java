package com.departments.dao;



import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;

import java.util.List;

/**
 * Created by alex on 8.2.17.
 */
public interface DepartmentDao {
    public Department findDepartmentById(Long id);
    public Long save(Department department);
    public void delete(Long id);
    public void update(Department department);
    public List<Department> findAllDepartments();
    public List<DepartmentsWithAvgSalary> findDepartmentsWithAvgSalary();
}