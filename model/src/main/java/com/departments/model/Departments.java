package com.departments.model;

import java.util.ArrayList;

/**
 * Created by alex on 9.2.17.
 */
public class Departments {
    private ArrayList<Department> departments=null;
    private Long totalDepartment=null;

    public Departments() {
    }
    public Departments(ArrayList<Department> departments){
        this.departments=departments;
    }
    public ArrayList<Department> getDepartments() {
        return departments;
    }
    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }
    public Long getTotalCount(){
        return totalDepartment;
    }
    public void setTotalDepartment(Long totalDepartment){
        this.totalDepartment=totalDepartment;
    }
}
