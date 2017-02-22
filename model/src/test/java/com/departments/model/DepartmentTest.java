package com.departments.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

/**
 * Created by alex on 13.2.17.
 */
public class DepartmentTest {
    private static final Logger log= LoggerFactory.getLogger(Department.class);

    private Department department;
    private Long id;
    private String nameDepartment;


    @Before
    public void setUp() throws Exception{
        this.department=new Department();
        this.id=1L;
        this.nameDepartment="credit";

    }

    @Test
    public void getIdIsShouldTrue()  {
        log.debug("start test getIdIsShouldTrue()");
        department.setId(id);
        assertEquals(id,department.getId(),0);
    }


    @Test
    public void getNameDepartmentIsShouldTrue()  {
        log.debug("start test getNameDepartmentIsShouldTrue()");
        department.setNameDepartment(nameDepartment);
        assertEquals(nameDepartment,department.getNameDepartment());

    }

    @Test
    public void equalsDepartmentIsShouldTrue(){
        log.debug("start test equalsDepartmentIsShouldTrue()");
        department.setId(id);
        department.setNameDepartment(nameDepartment);
        Assert.assertTrue(department.equals(department));

    }


}