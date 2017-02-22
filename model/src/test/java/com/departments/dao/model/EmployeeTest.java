package com.departments.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by alex on 13.2.17.
 */
public class EmployeeTest {
    private static final Logger log= LoggerFactory.getLogger(EmployeeTest.class);

    private Employee employee;
    private Long id;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private Integer salary;
    private Long idDepartment;

    @Before
    public void setUp() throws Exception{
        this.employee=new Employee();
        this.id=1L;
        this.firstName="Bob";
        this.lastName="Wolf";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse("2014-05-03");
        this.dob=date;
        this.salary=1000;
        this.idDepartment=3L;


    }
    @Test
    public void getIdIsShouldTrue() throws Exception {
        log.debug("start test getIdIsShouldTrue()");
        employee.setId(id);
        Assert.assertEquals(id,employee.getId());
    }

    @Test
    public void getFirstNameIsShouldTrue() throws Exception {
        log.debug("start test getFirstNameIsShouldTrue()");
        employee.setFirstName(firstName);
        Assert.assertEquals(firstName,employee.getFirstName());

    }

    @Test
    public void getLastNameIsShouldTrue() throws Exception {
        log.debug("start test getLastNameIsShouldTrue()");
        employee.setLastName(lastName);
        Assert.assertEquals(lastName,employee.getLastName());

    }

    @Test
    public void getDobIsShouldTrue() throws Exception {
        log.debug("start test getDobIsShouldTrue()");
        employee.setDob(dob);
        Assert.assertEquals(dob,employee.getDob());

    }

    @Test
    public void getSalaryIsShouldTrue() throws Exception {
        log.debug("start test getSalaryIsShouldTrue()");
        employee.setSalary(salary);
        Assert.assertEquals(salary,employee.getSalary());
    }

    @Test
    public void getIdDepartmentIsShouldTrue() throws Exception {
        log.debug("start test getIdDepartmentIsShouldTrue()");
        employee.setIdDepartment(idDepartment);
        Assert.assertEquals(idDepartment,employee.getIdDepartment());

    }
    @Test
    public void equalsEmployeeIsShouldTrue(){
        log.debug("start test equalsEmployeeIsShouldTrue()");
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDob(dob);
        employee.setSalary(salary);
        employee.setIdDepartment(idDepartment);
        Assert.assertTrue(employee.equals(employee));

    }

}