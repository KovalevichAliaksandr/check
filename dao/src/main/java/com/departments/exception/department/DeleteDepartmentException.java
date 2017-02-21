package com.departments.exception.department;

/**
 * Created by ALex on 19.02.2017.
 */
public class DeleteDepartmentException extends DepartmentException {
    private Long id;

    public DeleteDepartmentException(Long id){
        super("Delete department with id "+id+" error");
        this.id=id;
    }
    public Long getId(){ return id; }
}
