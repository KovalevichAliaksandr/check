package com.departments.controller;

import com.departments.model.EmployeeWithDepartment;
import com.departments.service.EmployeeService;
import com.departments.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alex on 10.2.17.
 */
@Controller
@RequestMapping(value = "/employee")
@CrossOrigin
public class EmployeeController implements EmployeeControllerInterface {
    private static final Logger log= LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
          this.employeeService=employeeService;
    }


    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }

    @Override
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FOUND)
    @RequestMapping(value = "/listEmployees",method = RequestMethod.GET)
    public List<Employee> listData(){
        return employeeService.findAllEmployees();
    }

    @Override
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FOUND)
    @RequestMapping(value = "/listEmployeesWithDepartments",method = RequestMethod.GET)
    public List<EmployeeWithDepartment> listDataWithDepartments(){
        return employeeService.findAllEmployeesWithDepartments();
    }

    @Override
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FOUND)
    @RequestMapping(value = "/listEmployeesWithFilter/{startDate}/{endDate}",method = RequestMethod.GET)
    public List<EmployeeWithDepartment> listEmployeesWithFilter(@PathVariable String startDate, @PathVariable String endDate){
        return employeeService.findAllEmployeesWithFilter(convertStringToDate(startDate),convertStringToDate(endDate));
    }

    private Date convertStringToDate(String stringDate){
        Date date=null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FOUND)
    @RequestMapping(value = "/getEmployeeWithDepartment/{id}",method = RequestMethod.GET)
    public EmployeeWithDepartment findEmployeeWithDepartmentById(@PathVariable Long id){
        return employeeService.findEmployeeWithDepartmentById(id);
    }

    @Override
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FOUND)
    @RequestMapping(value = "/getEmployee/{id}",method = RequestMethod.GET)
    public Employee findEmployeeById(@PathVariable Long id){
        return employeeService.findEmployeeById(id);
    }

    @Override
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/createEmployee",method = RequestMethod.POST)
    public Long create(@RequestBody Employee employee){
        log.debug("Create employee " , employee);
        Long id=employeeService.save(employee);
        log.debug("Employee create successfully with info{}", employee );
        return id;
    }

    @Override
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/updateEmployee/{id}",method = RequestMethod.PUT)
    public Employee update(@RequestBody Employee employee, @PathVariable Long id){
        log.debug("Update employee {}" , employee);
        employee.setId(id);
        employeeService.update(employee);
        log.debug("Employee updated successfully with info {}", employee );
        return employee;
    }

    @Override
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/deleteEmployee/{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        Employee employee=employeeService.findEmployeeById(id);
        log.debug("Delete employee {}",employee);
        employeeService.delete(id);
        log.debug("Delete employee successfully {}",employee);
    }

}

