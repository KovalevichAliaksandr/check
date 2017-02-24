package com.departments.service;

import com.departments.dao.DepartmentDao;
import com.departments.dao.EmployeeDao;
import com.departments.model.Department;
import com.departments.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by alex on 20.2.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImplTest.class);

    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeServiceImpl employeeService;

    Employee employee1;
    Employee employee2;
    Date nextDay;

    @Before
    public void setUp() {
        employee1 = new Employee(1L, "firstName1", "lastName1", Timestamp.valueOf("1944-05-18 00:00:00"), 5000, 1L);
        employee2 = new Employee(2L, "firstName2", "lastName2", Timestamp.valueOf("1954-12-08 00:00:00"), 8000, 2L);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,1);
        nextDay=calendar.getTime();
    }

    @Test
    public void findEmployeeByIdShouldReturnTrue() throws Exception {
        when(employeeDao.findEmployeeById(1L)).thenReturn(employee1);
        assertEquals(employee1, employeeService.findEmployeeById(1L));
        verify(employeeDao, times(1)).findEmployeeById(1L);
    }

    @Test
    public void findAllEmployeesShouldReturnTrue() throws Exception {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        when(employeeDao.findAllEmployees()).thenReturn(employeeList);
        assertEquals(employeeList.size(), employeeService.findAllEmployees().size());
        verify(employeeDao, times(1)).findAllEmployees();
    }

    @Test
    public void saveShouldReturnTrue() throws Exception {
        when(employeeDao.save(employee1)).thenReturn(1L);
        assertEquals(1L, (long) employeeService.save(employee1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveNullEmployeeShouldReturnIllegalArgumentException() throws Exception {
        employeeService.save(new Employee());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveNullFirstNameShouldReturnIllegalArgumentException() throws Exception {
        employee1.setFirstName(null);
        employeeService.save(employee1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveNullLastNameShouldReturnIllegalArgumentException() throws Exception {
        employee1.setLastName(null);
        employeeService.save(employee1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveLessThanZeroSalaryReturnIllegalArgumentException() throws Exception {
        employee1.setSalary(-1);
        employeeService.save(employee1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveWrongBodReturnIllegalArgumentException() throws Exception {
        employee1.setDob(nextDay);
        employeeService.save(employee1);
    }

    @Test
    public void deleteShouldReturnTrue() throws Exception {
        employeeService.delete(1L);
        verify(employeeDao, times(1)).delete(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNullIdReturnIllegalArgumentException() throws Exception {
        employeeService.delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteIdLessThanZeroReturnIllegalArgumentException() throws Exception {
        employeeService.delete(-1L);
    }

    @Test
    public void updateShouldReturnTrue() throws Exception {
        employeeService.update(employee1);
        verify(employeeDao, times(1)).update(employee1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullEmployeeShouldReturnIllegalArgumentException() throws Exception {
        employeeService.update(new Employee());
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullFirstNameShouldReturnIllegalArgumentException() throws Exception {
        employee1.setFirstName(null);
        employeeService.update(employee1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullLastNameShouldReturnIllegalArgumentException() throws Exception {
        employee1.setLastName(null);
        employeeService.update(employee1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateLessThanZeroSalaryReturnIllegalArgumentException() throws Exception {
        employee1.setSalary(-1);
        employeeService.update(employee1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWrongBodReturnIllegalArgumentException() throws Exception {
        employee1.setDob(nextDay);
        employeeService.update(employee1);
    }

}