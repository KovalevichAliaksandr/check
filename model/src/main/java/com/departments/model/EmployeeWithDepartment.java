package com.departments.model;

import java.util.Date;

/**
 * The class(DTO) is used to pass the employee and the department name  on it between
 * the layers of the program.
 */
public class EmployeeWithDepartment extends Employee{

private String nameDepartment;

    public EmployeeWithDepartment() {
    }

    public EmployeeWithDepartment(String firstName, String lastName, Long idDepartment, String nameDepartment) {
        super(firstName, lastName, idDepartment);
        this.nameDepartment = nameDepartment;
    }

    public EmployeeWithDepartment(Long id, String firstName, String lastName, Date dob, Integer salary, Long idDepartment, String nameDepartment) {
        super(id, firstName, lastName, dob, salary, idDepartment);
        this.nameDepartment = nameDepartment;
    }

    public EmployeeWithDepartment(String firstName, String lastName, Date dob, Integer salary, Long idDepartment, String nameDepartment) {
        super(firstName, lastName, dob, salary, idDepartment);
        this.nameDepartment = nameDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

}

