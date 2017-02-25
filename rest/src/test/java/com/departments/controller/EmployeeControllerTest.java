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
import org.springframework.test.web.servlet.MvcResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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


    public static final String URL_EMPLOYEE_LIST = "/employee/listEmployees";
    public static final String URL_GET_EMPLOYEE_1 = "/employee/getEmployee/1";
    public static final String URL_CREATE_EMPLOYEE = "/employee/createEmployee";
    public static final String URL_UPDATE_EMPLOYEE_1 = "/employee/updateEmployee/1";
    public static final String URL_DELETE_EMPLOYEE_1 = "/employee/deleteEmployee/1";

    public static final String EMPLOYEE_STRING = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"dob\":\"1944-05-17\",\"salary\":5000,\"idDepartment\":2}";
    public static final String EMPLOYEE_LIST ="["+EMPLOYEE_STRING+"]";

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
    public void listDataShouldReturnStatusIsFound() throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeService.findAllEmployees()).thenReturn(employeeList);

        this.mockMvc.perform(get(URL_EMPLOYEE_LIST)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string(EMPLOYEE_LIST));
        verify(employeeService, times(1)).findAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void findEmployeeByIdShouldReturnStatusIsFound() throws Exception {
        when(employeeService.findEmployeeById(1L)).thenReturn(employee);
        this.mockMvc.perform(get(URL_GET_EMPLOYEE_1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isFound())
                .andExpect(content().string(EMPLOYEE_STRING));
        verify(employeeService, times(1)).findEmployeeById(1L);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void createShouldReturnStatusIsCreated() throws Exception {
        when(employeeService.save(any(Employee.class))).thenReturn(1L);
        this.mockMvc
                .perform(post(URL_CREATE_EMPLOYEE)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(EMPLOYEE_STRING))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
        verify(employeeService, times(1)).save(any(Employee.class));
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void updateShouldReturnStatusIsOk() throws Exception {
        this.mockMvc
                .perform(put(URL_UPDATE_EMPLOYEE_1)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(EMPLOYEE_STRING))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteByShouldReturnStatusIsOk() throws Exception {
        this.mockMvc
                .perform(delete(URL_DELETE_EMPLOYEE_1)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void checkWrongMediaTypeShouldReturnStatusIs3xxRedirection() throws Exception {
        this.mockMvc
                .perform(get(URL_GET_EMPLOYEE_1)
                        .accept(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
    @Test
    public void checkWrongUrlShouldReturnStatusIsNotFound() throws Exception {

        this.mockMvc
                .perform(post(URL_CREATE_EMPLOYEE+1)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(EMPLOYEE_STRING))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

    }
}