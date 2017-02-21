package com.departments.dao.exception.employee;

/**
 * Created by ALex on 19.02.2017.
 */
public class UpdateEmployeeException extends EmployeeException {
    private Long id;

    public UpdateEmployeeException(Long id ){
        super("Update employee with  id = "+id+"error");
        this.id=id;
    }
    public String getId(){
        return getId();
    }
}
