package com.departments.model;

import java.util.Date;

/**
 * The class(DTO) is used to pass the employee and the department name  on it between
 * the layers of the program.
 */
public class EmployeeWithDepartment {
    private Employee employee;

    private String nameDepartment;

    public EmployeeWithDepartment() {
    }

    public EmployeeWithDepartment(Employee employee, String nameDepartment) {
        this.employee = employee;
        this.nameDepartment = nameDepartment;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }
}



