package com.departments.dao.controller;

import com.departments.dao.model.DepartmentsWithAvgSalary;
import com.departments.dao.service.DepartmentService;
import com.departments.dao.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alex on 9.2.17.
 */
@Controller
@RequestMapping(value = "/department")
public class DepartmentController {

    private static final Logger log= LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;

    @ResponseBody
    @RequestMapping(value = "/listDepartments",method = RequestMethod.GET)
    public List<Department> listDepartments(){
        return  departmentService.findAllDepartments() ;
    }

    @ResponseBody
    @RequestMapping(value = "/listDepartmentsWitAvgSalary",method = RequestMethod.GET)
    public List<DepartmentsWithAvgSalary> listDepartmentsWitAvgSalary(){
        return  departmentService.findDepartmentsWithAvgSalary();
    }

    @ResponseBody
    @RequestMapping(value = "/getDepartment/{id}",method = RequestMethod.GET)
    public Department findContactById(@PathVariable Long id){
        return departmentService.findDepartmentById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/createDepartment",method = RequestMethod.POST)
    public Department create (@RequestBody Department department){
        log.debug("Create department " , department);
        departmentService.save(department);
        log.debug("Department create successfully with info{}", department );
        return department;
    }

    @ResponseBody
    @RequestMapping(value = "/updateDepartment/{id}",method = RequestMethod.PUT)
    public Department update (@RequestBody Department department, @PathVariable Long id){
        log.debug("Update department {}" , department);
        department.setId(id);
        departmentService.update(department);
        log.debug("Department updated successfully with info {}", department );
        return department;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDepartment/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        Department department=departmentService.findDepartmentById(id);
        log.debug("Delete department {}",department);
        departmentService.delete(id);
        log.debug("Delete department successfully {}",department);
    }

}
