package com.departments.controller;

import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import com.departments.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alex on 9.2.17.
 */
@Controller
@RequestMapping(value = "/department")
@CrossOrigin
public class DepartmentController implements DepartmentControllerInterface {

    private static final Logger log= LoggerFactory.getLogger(DepartmentControllerInterface.class);

    @Autowired
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService=departmentService;
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }

    @Override
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FOUND)
    @RequestMapping(value = "/listDepartments",method = RequestMethod.GET)
    public List<Department> listDepartments(){
        return  departmentService.findAllDepartments() ;
    }

    @Override
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FOUND)
    @RequestMapping(value = "/listDepartmentsWitAvgSalary",method = RequestMethod.GET)
    public List<DepartmentsWithAvgSalary> listDepartmentsWitAvgSalary(){
        return  departmentService.findDepartmentsWithAvgSalary();
    }

    @Override
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FOUND)
    @RequestMapping(value = "/getDepartment/{id}",method = RequestMethod.GET)
    public Department findContactById(@PathVariable Long id){
        return departmentService.findDepartmentById(id);
    }

    @Override
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/createDepartment",method = RequestMethod.POST)
    public  Long create(@RequestBody Department department){
        log.debug("Create department " , department);
        Long id=departmentService.save(department);
        log.debug("Department create successfully with id = {}", id );
        return id;
    }

    @Override
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/updateDepartment/{id}",method = RequestMethod.PUT)
    public Department update(@RequestBody Department department, @PathVariable Long id){
        log.debug("Update department {}" , department);
        department.setId(id);
        departmentService.update(department);
        log.debug("Department updated successfully with info {}", department );
        return department;
    }

    @Override
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/deleteDepartment/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        Department department=departmentService.findDepartmentById(id);
        log.debug("Delete department {}",department);
        departmentService.delete(id);
        log.debug("Delete department successfully {}",department);
    }

}
