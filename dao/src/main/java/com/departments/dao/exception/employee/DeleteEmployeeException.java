package com.departments.dao.exception.employee;

/**
 * Created by ALex on 19.02.2017.
 */
public class DeleteEmployeeException extends EmployeeException {
    private Long id;

    public DeleteEmployeeException(Long id){
        super("Delete department with id "+id+" error");
        this.id=id;
    }
    public Long getId(){ return id; }
}
