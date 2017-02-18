package com.departments.dao.exception;

/**
 * Created by alex on 18.2.17.
 */
public abstract class DepartmentException  extends Exception{

    public DepartmentException(String message){
        super(message);
    }
    public DepartmentException(String message,Throwable cause){
        super(message,cause);
    }

}
