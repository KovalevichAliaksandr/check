package com.departments.dao;


import com.departments.dao.model.Department;
import com.departments.dao.model.DepartmentsWithAvgSalary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ALex on 19.02.2017.
 */
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:test-spring-db.xml"})
@ContextConfiguration(locations={"classpath*:test-spring-dao.xml", "classpath*:test-spring-db.xml"})
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception{
//        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("classpath*:test-spring-db.xml");
//        this.departmentDao=(DepartmentDao) applicationContext.getBean("departmentDao");
          }

    @Test
    public void findDepartmentByIdShouldReturnTrue() throws Exception {
        Department department = departmentDao.findDepartmentById(1L);
        assertEquals("credit",department.getNameDepartment());
    }


}