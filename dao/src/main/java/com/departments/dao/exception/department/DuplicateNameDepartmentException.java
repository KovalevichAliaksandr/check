package com.departments.dao.exception.department;

/**
 * Created by alex on 18.2.17.
 */
public class DuplicateNameDepartmentException  extends DepartmentException{
    private String nameDepartment;

    public DuplicateNameDepartmentException(String nameDepartment){
        super("Department name "+nameDepartment+" already exists");
        this.nameDepartment=nameDepartment;
    }
    public String getNameDepartment(){
        return nameDepartment;
    }
}
