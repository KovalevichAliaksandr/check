package com.departments.dao.exception.department;

/**
 * Created by alex on 20.2.17.
 */
public class FindDepartmentByIdException extends DepartmentException {
    private Long id;

    public FindDepartmentByIdException(Long id){
        super("Not found department with id "+id+" - error");
        this.id=id;
    }
    public Long getId(){ return id; }
}
