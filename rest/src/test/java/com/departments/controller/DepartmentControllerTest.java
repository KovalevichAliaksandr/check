package com.departments.controller;

import com.departments.dao.DepartmentDao;
import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
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
import static org.mockito.Mockito.when;
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


    public static final String URL_DEPARTMENT_LIST_DEPARTMENTS = "/department/listDepartments";
    public static final String URL_DEPARTMENT_LIST_DEPARTMENTS_WIT_AVG_SALARY = "/department/listDepartmentsWitAvgSalary";
    public static final String URL_DEPARTMENT_GET_DEPARTMENT_1 = "/department/getDepartment/1";
    public static final String URL_DEPARTMENT_CREATE_DEPARTMENT = "/department/createDepartment";
    public static final String URL_DEPARTMENT_UPDATE_DEPARTMENT_1 = "/department/updateDepartment/1";
    public static final String URL_DEPARTMENT_DELETE_DEPARTMENT_1 = "/department/deleteDepartment/1";

    public static final String DEPARTMENTS_LIST = "[{\"id\":1,\"nameDepartment\":\"credit\"}]";
    public static final String DEPARTMENTS_LIST_AVG_SALARY = "[{\"id\":1,\"nameDepartment\":\"credit\",\"avgSalary\":300}]";
    public static final String DEPARTMENT_STRING = "{\"id\":1,\"nameDepartment\":\"credit\"}";
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private MockMvc mockMvc;
    private Department department1;
    private String departmentString;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(departmentController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        department1 = new Department(1, "credit");
    }


    @Test
    public void listDepartments() throws Exception {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department1);
        when(departmentService.findAllDepartments()).thenReturn(departmentList);

        this.mockMvc.perform(get(URL_DEPARTMENT_LIST_DEPARTMENTS)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string(DEPARTMENTS_LIST));
    }

    @Test
    public void listDepartmentsWitAvgSalary() throws Exception {
        List<DepartmentsWithAvgSalary> departmentsWithAvgSalaryList = new ArrayList<>();
        departmentsWithAvgSalaryList.add(new DepartmentsWithAvgSalary(1L, "credit", 300));
        when(departmentService.findDepartmentsWithAvgSalary()).thenReturn(departmentsWithAvgSalaryList);

        this.mockMvc.perform(get(URL_DEPARTMENT_LIST_DEPARTMENTS_WIT_AVG_SALARY)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string(DEPARTMENTS_LIST_AVG_SALARY));
    }

    @Test
    public void findContactById() throws Exception {
        when(departmentService.findDepartmentById(1L)).thenReturn(department1);
        this.mockMvc.perform(get(URL_DEPARTMENT_GET_DEPARTMENT_1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string(DEPARTMENT_STRING));
    }

    @Test
    public void create() throws Exception {
        when(departmentService.save(any(Department.class))).thenReturn(1L);
        this.mockMvc
                .perform(post(URL_DEPARTMENT_CREATE_DEPARTMENT)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DEPARTMENT_STRING))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));

    }

    @Test
    public void update() throws Exception {
        this.mockMvc
                .perform(put(URL_DEPARTMENT_UPDATE_DEPARTMENT_1)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(DEPARTMENT_STRING))
                .andDo(print())
                .andExpect(status().isOk());



    }

    @Test
    public void deleteShouldBy() throws Exception {
        this.mockMvc
                .perform(delete(URL_DEPARTMENT_DELETE_DEPARTMENT_1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}