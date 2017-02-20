package com.departments.service;

import com.departments.dao.DepartmentDao;
import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 20.2.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImplTest {

    @Mock
    DepartmentDao departmentDao;
    @InjectMocks
    DepartmentServiceImpl departmentService;

    Department department1;
    Department department2;

    @Before
    public void setUp(){
        department1=new Department(1,"credit");
        department2=new Department(2,"cash");
    }
    @Test
    public void findDepartmentByIdShouldReturnTrue() throws Exception {
        when(departmentDao.findDepartmentById(1L)).thenReturn(department1);
        assertEquals(department1,departmentService.findDepartmentById(1L));
        verify(departmentDao, times(1)).findDepartmentById(1L);
    }

    @Test
    public void findAllDepartmentsShouldReturnTrue() throws Exception {
        List<Department> departmentList=new ArrayList<Department>();
        departmentList.add(department1);
        departmentList.add(department2);

        when(departmentDao.findAllDepartments()).thenReturn(departmentList);
        assertEquals(departmentList.size(),departmentService.findAllDepartments().size());
        verify(departmentDao, times(1)).findAllDepartments();
    }

    @Test
    public void findDepartmentsWithAvgSalaryShouldReturnTrue() throws Exception {
        List<DepartmentsWithAvgSalary> departmentsWithAvgSalaryList=new ArrayList<>();
        departmentsWithAvgSalaryList.add(new DepartmentsWithAvgSalary(1L,"credit",100));
        departmentsWithAvgSalaryList.add(new DepartmentsWithAvgSalary(2L,"cash",200));

        when(departmentDao.findDepartmentsWithAvgSalary()).thenReturn(departmentsWithAvgSalaryList);
        assertEquals(departmentsWithAvgSalaryList.size(),departmentService.findDepartmentsWithAvgSalary().size());
        verify(departmentDao, times(1)).findDepartmentsWithAvgSalary();
    }

    @Test
    public void saveShouldReturnTrue() throws Exception {
        when(departmentDao.save(department1)).thenReturn(1L);
        assertEquals(1L,(long)departmentService.save(department1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveNullNameDeparmentShouldReturnIllegalArgumentException() throws Exception {
        departmentService.save(new Department());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteDepartmentWithNullIdIllegalArgumentException() throws Exception {
        departmentService.delete(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateDepartmentWithNullIdReturnIllegalArgumentException() throws Exception {

        departmentService.update(new Department(0,"new department"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void updateDepartmentWithNullNameReturnIllegalArgumentException() throws Exception {
        departmentService.update(new Department(0,null));

    }


    @Test
    public void updateShouldReturnTrue() throws Exception {
        departmentService.update(department1);
        verify(departmentDao, times(1)).update(department1);
    }

}