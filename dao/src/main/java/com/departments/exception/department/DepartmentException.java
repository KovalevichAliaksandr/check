package com.departments.exception.department;

/**
 * Created by alex on 18.2.17.
 */
public abstract class DepartmentException  extends RuntimeException{

    public DepartmentException(String message){
        super(message);
    }


}
