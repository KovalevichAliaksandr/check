package com.departments.model;

import java.util.ArrayList;

/**
 * Created by alex on 9.2.17.
 */
public class Employees {
    private ArrayList<Employee> employees;
    private Long totalEmployee;

    public Employees() {
    }

    public Employees(ArrayList<Employee> employees){
        this.employees=employees;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public Long getTotalEmployee(){
        return totalEmployee;
    }

    public void setTotalEmployee(Long totalEmployee){
        this.totalEmployee=totalEmployee;
    }
}
