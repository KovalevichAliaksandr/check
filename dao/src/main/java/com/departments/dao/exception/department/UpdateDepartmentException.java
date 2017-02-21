package com.departments.dao.exception.department;

/**
 * Created by ALex on 19.02.2017.
 */
public class UpdateDepartmentException extends DepartmentException {
    private String nameDepartment;

    public UpdateDepartmentException(String nameDepartment){
        super("Update department name "+nameDepartment+"error");
        this.nameDepartment=nameDepartment;
    }
    public String getNameDepartment(){
        return nameDepartment;
    }
}
