package com.departments.dao;

import com.departments.model.Department;
import com.departments.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by alex on 20.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})
@Transactional
public class EmployeeDaoImplTest {

    @Autowired
    EmployeeDao employeeDao;


    @Test
    public void findEmployeeByIdShouldReturnTrue() throws Exception {
        Employee employee = employeeDao.findEmployeeById(1L);
        assertEquals(1,(long)employee.getId());
        assertEquals("Scott",employee.getFirstName());
        assertEquals("Wolf",employee.getLastName());
        assertEquals(Timestamp.valueOf("1944-05-18 00:00:00"),employee.getDob());
        assertEquals(5000,(long)employee.getSalary());
        assertEquals(1,(long)employee.getIdDepartment());

    }
    @Test(expected = RuntimeException.class)
    public void findEmployeeByIdShouldReturnExceptionMessage() throws Exception {
        Employee testEmployee = employeeDao.findEmployeeById(3L);
        assertNull(testEmployee);
    }


    @Test
    public void findAllEmployeesShouldReturnTrue() throws Exception {
        List<Employee> employees = employeeDao.findAllEmployees();
        assertTrue(employees.size() == 2);
    }

    @Test
    public void saveShouldReturnTrue() throws Exception {
        Employee employee = new Employee("New firstName","new lastName",new Date(),8000,2L);
        employeeDao.save(employee);
        List<Employee> employees = employeeDao.findAllEmployees();
        assertEquals(employee, employees.get(2));
    }

    @Test
    public void deleteShouldReturnTrue() throws Exception {
        long countUserBefore=  employeeDao.findAllEmployees().size();
        employeeDao.delete(2L);
        long countUserAfter=employeeDao.findAllEmployees().size();
        assertEquals(countUserBefore, countUserAfter+1);
    }

     @Test
    public void updateShouldReturnTrue() throws Exception {
         Employee employee=new Employee(2L,"New firstName","new lastName",new Date(),8000,2L);
         Employee oldEmployee=employeeDao.findEmployeeById(2L);
         employeeDao.update(employee);
         assertNotEquals(oldEmployee,employee);

         Employee newEmployee=employeeDao.findEmployeeById(2L);
         employeeDao.update(employee);
         assertEquals(employee,newEmployee);
    }

}