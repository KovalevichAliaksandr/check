package com.departments.controller;

import com.departments.service.EmployeeService;
import com.departments.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10.2.17.
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    private static final Logger log= LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value = "/listEmployees",method = RequestMethod.GET)
    public List<Employee> listData(){
        return employeeService.findAllEmployees();
    }

    @ResponseBody
    @RequestMapping(value = "/getEmployee/{id}",method = RequestMethod.GET)
    public Employee findContactById(@PathVariable Long id){
        return employeeService.findEmployeeById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/createEmployee",method = RequestMethod.POST)
    public Employee create (@RequestBody Employee employee){
        log.debug("Create employee " , employee);
        employeeService.save(employee);
        log.debug("Employee create successfully with info{}", employee );
        return employee;
    }

    @ResponseBody
    @RequestMapping(value = "/updateEmployee/{id}",method = RequestMethod.PUT)
    public Employee update (@RequestBody Employee employee, @PathVariable Long id){
        log.debug("Update employee {}" , employee);
        employee.setId(id);
        employeeService.update(employee);
        log.debug("Employee updated successfully with info {}", employee );
        return employee;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteEmployee/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        Employee employee=employeeService.findEmployeeById(id);
        log.debug("Delete employee {}",employee);
        employeeService.delete(id);
        log.debug("Delete employee successfully {}",employee);
    }

}

