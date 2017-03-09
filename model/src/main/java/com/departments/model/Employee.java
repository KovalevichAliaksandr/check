package com.departments.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by alex on 7.2.17.
 */
public class Employee {


    private Long id;

    @NotNull(message =" First name not be null")
    @Size(min = 1,max = 45,message="First name should be between 1 - 45 symbols")
    private String firstName;

    @NotNull(message =" Last name not be null")
    @Size(min = 1,max = 45,message="Last name should be between 1 - 45 symbols")
    private String lastName;

    @Past(message =" Day of birth  should  be  less than the current date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @NotNull(message =" Day of birth  should  be  not null")
    private Date dob;

    //    @Pattern(regexp="[\\d]{*}",message = "Salary should by digital")
    @Min(value = 0,message="Salary  should be  >=0 ")
    @NotNull(message="Salary  should be not null")
    private Integer salary;

    @NotNull(message="department  should be  not null ")
    @Min(value = 1,message="department  id should be  ")
    private Long idDepartment;

    public Employee() {

    }

    public Employee(String firstName, String lastName, Long idDepartment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idDepartment = idDepartment;
    }

    public Employee(Long id, String firstName, String lastName, Date dob, Integer salary, Long idDepartment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.salary = salary;
        this.idDepartment = idDepartment;
    }

    public Employee(String firstName, String lastName, Date dob, Integer salary, Long idDepartment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.salary = salary;
        this.idDepartment = idDepartment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", salary=" + salary +
                ", idDepartment=" + idDepartment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(dob, employee.dob) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(idDepartment, employee.idDepartment);
    }

}
