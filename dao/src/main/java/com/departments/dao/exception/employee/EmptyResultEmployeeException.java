package com.departments.dao.exception.employee;

/**
 * Created by alex on 20.2.17.
 */
public class EmptyResultEmployeeException extends EmployeeException {
    private Long id;

    public EmptyResultEmployeeException(Long id ){
        super("Find employee with  id = "+id+"error");
        this.id=id;
    }
    public String getId(){
        return getId();
    }
}
