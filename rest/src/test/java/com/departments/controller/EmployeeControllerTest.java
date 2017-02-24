package com.departments.controller;

import com.departments.model.Department;
import com.departments.model.Employee;
import com.departments.service.DepartmentService;
import com.departments.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by alex on 24.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-rest.xml"})
public class EmployeeControllerTest {


    public static final String URL_EMPLOYEE_LIST_EMPLOYEES = "/employee/listEmployees";
    public static final String URL_EMPLOYEE_GET_EMPLOYEE_1 = "/employee/getEmployee/1";
    public static final String URL_EMPLOYEE_CREATE_EMPLOYEE = "/employee/createEmployee";
    public static final String URL_EMPLOYEE_UPDATE_EMPLOYEE_1 = "/employee/updateEmployee/1";
    public static final String URL_EMPLOYEE_DELETE_EMPLOYEE_1 = "/employee/deleteEmployee/1";
    public static final String EMPLOYEE_STRING = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"dob\":\"1944-05-17\",\"salary\":5000,\"idDepartment\":2}";
    public static final String EMPLOYEE_LIST = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"dob\":\"1944-05-17\",\"salary\":5000,\"idDepartment\":2}]"
            ;
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;
    private Employee employee;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(employeeController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        employee = new Employee(1L, "John", "Smith", Timestamp.valueOf("1944-05-18 00:00:00"), 5000, 2L);

    }

    @Test
    public void listData() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeService.findAllEmployees()).thenReturn(employeeList);

        this.mockMvc.perform(get(URL_EMPLOYEE_LIST_EMPLOYEES)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string(EMPLOYEE_LIST));
    }

    @Test
    public void findEmployeeById() throws Exception {
        when(employeeService.findEmployeeById(1L)).thenReturn(employee);
        this.mockMvc.perform(get(URL_EMPLOYEE_GET_EMPLOYEE_1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string(EMPLOYEE_STRING));

    }

    @Test
    public void create() throws Exception {
        when(employeeService.save(any(Employee.class))).thenReturn(1L);
        this.mockMvc
                .perform(post(URL_EMPLOYEE_CREATE_EMPLOYEE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(EMPLOYEE_STRING))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));

    }

    @Test
    public void update() throws Exception {
        this.mockMvc
                .perform(put(URL_EMPLOYEE_UPDATE_EMPLOYEE_1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(EMPLOYEE_STRING))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void deleteBy() throws Exception {
        this.mockMvc
                .perform(delete(URL_EMPLOYEE_DELETE_EMPLOYEE_1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}