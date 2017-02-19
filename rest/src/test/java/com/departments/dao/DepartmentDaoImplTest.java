package com.departments.dao;

import com.departments.dao.exception.DuplicateNameDepartmentException;
import com.departments.dao.exception.department.DeleteDepartmentException;
import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ALex on 19.02.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-db.xml"})
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
    @Transactional
    @Rollback(true)
    public void saveShouldReturnTrue() throws Exception {
        Department department = new Department("Add new department");
        departmentDao.save(department);
        List<Department> departments = departmentDao.findAllDepartments();
        Assert.assertEquals(department.getNameDepartment(), departments.get(2).getNameDepartment());
    }


    @Transactional
    @Rollback(true)
//    @Test(expected = DuplicateNameDepartmentException.class)
    @Test(expected = RuntimeException.class)
    public void saveShouldReturnErrorMessage() throws Exception {
        Department department = new Department("Add new department");
        departmentDao.save(department);
        departmentDao.save(department);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteShouldReturnTrue() throws Exception {
        departmentDao.delete(2L);
        Department department = departmentDao.findDepartmentById(2L);
        assertNull("Department is not null.", department);
    }


    @Transactional
    @Rollback(true)
//    @Test(expected = RuntimeException.class)
    @Test(expected = DeleteDepartmentException.class)
//    @Test(expected = DataAccessException.class)
    public void deleteShouldReturnMessage() throws Exception {
        departmentDao.delete(1L);
        Department department = departmentDao.findDepartmentById(-1L);

//        List<User> users = userDao.getAllUsers();
//        Integer sizeBeforeDelete = users.size();
//
//        User user = userDao.getUserById(COUNT_OF_ALL_USERS);
//        assertNotNull(user);
//
//        userDao.deleteUser(COUNT_OF_ALL_USERS);
//
//        users = userDao.getAllUsers();
//        Integer sizeAfterDelete = users.size();
//        assertEquals(1,sizeBeforeDelete-sizeAfterDelete);
//        assertEquals(null,userDao.getUserById(COUNT_OF_ALL_USERS));
//
//
//        thrown.expect(Exception.class);
//        thrown.expectMessage(UserDaoImpl.ERR_USER_IS_NOT_EXIST);
//        userDao.deleteUser(COUNT_OF_ALL_USERS+1);


    }
    @Test
    @Transactional
    @Rollback(true)
    public void update() throws Exception {
//        User user = new User(
//                COUNT_OF_ALL_USERS+1,
//                "UpdatedUser",
//                "UpdatedPassword",
//                "UpdatedDescription");
//        thrown.expect(Exception.class);
//        thrown.expectMessage(UserDaoImpl.ERR_USER_IS_NOT_EXIST);
//        userDao.updateUser(user);
//
//        user.setUserId(COUNT_OF_ALL_USERS-1);
//        User oldUser = userDao.getUserById(COUNT_OF_ALL_USERS-1);
//        assertNotEquals(oldUser,user);
//
//        userDao.updateUser(user);
//        User newUser = userDao.getUserById(COUNT_OF_ALL_USERS-1);
//        assertEquals(user,newUser);
//
    }

    @Test
    public void findDepartmentsWithAvgSalary() throws Exception {
        List<DepartmentsWithAvgSalary> departmentsWithAvgSalaries = departmentDao.findDepartmentsWithAvgSalary();
        assertTrue(departmentsWithAvgSalaries.size() == 2);

    }

}