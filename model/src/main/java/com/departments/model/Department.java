package com.departments.model;





import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by alex on 7.2.17.
 */

public class Department implements Serializable {
    @AssertTrue(message = "Should by Name of department")
    public boolean isShouldNameOfDepartment(){
        Boolean result=true;
        if(nameDepartment==null){
            result=false;
        }
        return result;
    }

    @NotNull
    private long id ;
    @NotNull
    @Size(min = 1,max = 45)
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

    @Override
    public int hashCode() {
        return Objects.hash(id, nameDepartment);
    }
}
