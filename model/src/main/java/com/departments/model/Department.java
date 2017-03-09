package com.departments.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by alex on 7.2.17.
 */

public class Department implements Serializable {


    private long id ;
    @NotNull(message =" Department name not be null")
    @Size(min = 1,max = 45,message="Department name should be between 1 - 45 symbols")
    private String nameDepartment;

    public Department() {
    }

    public Department(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Department(long id, String nameDepartment) {
        this.id = id;
        this.nameDepartment = nameDepartment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", nameDepartment='" + nameDepartment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(nameDepartment, that.nameDepartment);
    }

}
