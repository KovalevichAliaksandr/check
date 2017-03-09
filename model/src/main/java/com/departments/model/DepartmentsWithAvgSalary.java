package com.departments.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The class (DTO) is used to pass the name of the department and the average salary on it between
 * the layers of the program.
 */
public class DepartmentsWithAvgSalary {

    private Department department;
    private Integer avgSalary;

    public DepartmentsWithAvgSalary() {
    }

    public DepartmentsWithAvgSalary(Department department, Integer avgSalary) {
        this.department = department;
        this.avgSalary = avgSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Integer avgSalary) {
        this.avgSalary = avgSalary;
    }

}