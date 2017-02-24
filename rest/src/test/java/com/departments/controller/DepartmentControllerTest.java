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
//        this.mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
        this.mockMvc = standaloneSetup(departmentController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        department1 = new Department(1, "credit");
        departmentString = "{\"id\":1,\"nameDepartment\":\"credit\"}";

    }


    @Test
    public void listDepartments() throws Exception {
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department1);
        when(departmentService.findAllDepartments()).thenReturn(departmentList);

        this.mockMvc.perform(get("/department/listDepartments")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string("[{\"id\":1,\"nameDepartment\":\"credit\"}]"));
    }

    @Test
    public void listDepartmentsWitAvgSalary() throws Exception {
        List<DepartmentsWithAvgSalary> departmentsWithAvgSalaryList = new ArrayList<>();
        departmentsWithAvgSalaryList.add(new DepartmentsWithAvgSalary(1L, "credit", 300));
        when(departmentService.findDepartmentsWithAvgSalary()).thenReturn(departmentsWithAvgSalaryList);

        this.mockMvc.perform(get("/department/listDepartmentsWitAvgSalary")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string("[{\"id\":1,\"nameDepartment\":\"credit\",\"avgSalary\":300}]"));
    }

    @Test
    public void findContactById() throws Exception {
        when(departmentService.findDepartmentById(1L)).thenReturn(department1);
        this.mockMvc.perform(get("/department/getDepartment/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().string(departmentString));
    }

    @Test
    public void create() throws Exception {
        when(departmentService.save(any(Department.class))).thenReturn(1L);
        this.mockMvc
                .perform(post("/department/createDepartment")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentString))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));

    }

    @Test
    public void update() throws Exception {
        this.mockMvc
                .perform(put("/department/updateDepartment/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentString))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void deleteShouldBy() throws Exception {
        this.mockMvc
                .perform(delete("/department/deleteDepartment/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}