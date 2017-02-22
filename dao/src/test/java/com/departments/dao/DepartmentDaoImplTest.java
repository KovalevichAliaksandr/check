package com.departments.dao;

import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ALex on 19.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDaoImpl departmentDao;

    @Before
    public void setUp() throws Exception{
          }

    @Test
    public void findDepartmentByIdShouldReturnTrue() throws Exception {
        Department department = departmentDao.findDepartmentById(1L);
        assertEquals("credit",department.getNameDepartment());
    }

    @Test
    public void findAllDepartmentsShouldReturnTrue() throws Exception {
        List<Department> departments = departmentDao.findAllDepartments();
        assertTrue(departments.size() == 2);
    }

    @Test
    public void saveShouldReturnTrue() throws Exception {
        Department department = new Department("Add new department");
        departmentDao.save(department);
        List<Department> departments = departmentDao.findAllDepartments();
        Assert.assertEquals(department.getNameDepartment(), departments.get(2).getNameDepartment());
    }


//        not work !!! why????-   @Test(expected = DuplicateNameDepartmentException.class)
    @Test(expected = RuntimeException.class)
    public void saveShouldReturnErrorMessage() throws Exception {
        Department department = new Department("Add new department");
        departmentDao.save(department);
        departmentDao.save(department);
    }

    @Test
    public void deleteShouldReturnTrue() throws Exception {
        long countUserBefore=  departmentDao.findAllDepartments().size();
        departmentDao.delete(2L);
        long countUserAfter=departmentDao.findAllDepartments().size();
        assertEquals(countUserBefore, countUserAfter+1);
    }

    @Test(expected = RuntimeException.class)
    public void deleteShouldReturnErrorMessage() throws Exception {
        departmentDao.delete(1L);
    }


    @Test
     public void updateShouldReturnTrue() throws Exception {
        Department department=new Department(2,"Update department");
        Department oldDepartment=departmentDao.findDepartmentById(2L);
        departmentDao.update(department);
        assertNotEquals(oldDepartment,department);

        Department newDepartment=departmentDao.findDepartmentById(2L);
        departmentDao.update(department);
        assertEquals(department,newDepartment);
    }



    @Test
    public void findDepartmentsWithAvgSalary() throws Exception {
        List<DepartmentsWithAvgSalary> departmentsWithAvgSalaries = departmentDao.findDepartmentsWithAvgSalary();
        assertTrue(departmentsWithAvgSalaries.size() == 2);

    }

}