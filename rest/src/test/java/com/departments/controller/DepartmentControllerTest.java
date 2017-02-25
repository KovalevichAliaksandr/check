package com.departments.controller;

import com.departments.dao.DepartmentDao;
import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import com.departments.model.Employee;
import com.departments.service.DepartmentService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


/**
 * Created by alex on 22.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-rest.xml"})
public class DepartmentControllerTest {


    public static final String URL_LIST_DEPARTMENTS = "/department/listDepartments";
    public static final String URL_LIST_DEPARTMENTS_WIT_AVG_SALARY = "/department/listDepartmentsWitAvgSalary";
    public static final String URL_GET_DEPARTMENT_1 = "/department/getDepartment/1";
    public static final String URL_CREATE_DEPARTMENT = "/department/createDepartment";
    public static final String URL_UPDATE_DEPARTMENT_1 = "/department/updateDepartment/1";
    public static final String URL_DELETE_DEPARTMENT_1 = "/department/deleteDepartment/1";

    public static final String DEPARTMENT_STRING = "{\"id\":1,\"nameDepartment\":\"credit\"}";
    public static final String DEPARTMENTS_LIST = "["+ DEPARTMENT_STRING+"]";
    public static final String DEPARTMENTS_LIST_AVG_SALARY = "[{\"id\":1,\"nameDepartment\":\"credit\",\"avgSalary\":300}]";

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private MockMvc mockMvc;
    private Department department1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(departmentController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        department1 = new Department(1, "credit");
    }

    @Test
    public void listDepartmentsShouldReturnStatusIsFound() throws Exception {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department1);
        when(departmentService.findAllDepartments()).thenReturn(departmentList);

        this.mockMvc.perform(get(URL_LIST_DEPARTMENTS)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isFound())
                .andExpect(content().string(DEPARTMENTS_LIST));
        verify(departmentService, times(1)).findAllDepartments();
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void listDepartmentsWitAvgShouldReturnStatusIsFound() throws Exception {
        List<DepartmentsWithAvgSalary> departmentsWithAvgSalaryList = new ArrayList<>();
        departmentsWithAvgSalaryList.add(new DepartmentsWithAvgSalary(1L, "credit", 300));
        when(departmentService.findDepartmentsWithAvgSalary()).thenReturn(departmentsWithAvgSalaryList);

        this.mockMvc.perform(get(URL_LIST_DEPARTMENTS_WIT_AVG_SALARY)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isFound())
                .andExpect(content().string(DEPARTMENTS_LIST_AVG_SALARY));
        verify(departmentService, times(1)).findDepartmentsWithAvgSalary();
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void findContactByIdShouldReturnStatusIsFound() throws Exception {
        when(departmentService.findDepartmentById(1L)).thenReturn(department1);
        this.mockMvc.perform(get(URL_GET_DEPARTMENT_1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isFound())
                .andExpect(content().string(DEPARTMENT_STRING));
        verify(departmentService, times(1)).findDepartmentById(1L);
        verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void createShouldReturnStatusIsCreated() throws Exception {
        when(departmentService.save(any(Department.class))).thenReturn(1L);
        this.mockMvc
                .perform(post(URL_CREATE_DEPARTMENT)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DEPARTMENT_STRING))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
        verify(departmentService, times(1)).save(any(Department.class));
        verifyNoMoreInteractions(departmentService);

    }

    @Test
    public void updateShouldReturnStatusIsOk() throws Exception {
        this.mockMvc
                .perform(put(URL_UPDATE_DEPARTMENT_1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DEPARTMENT_STRING))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteShouldReturnStatusIsOk() throws Exception {
        this.mockMvc
                .perform(delete(URL_DELETE_DEPARTMENT_1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}