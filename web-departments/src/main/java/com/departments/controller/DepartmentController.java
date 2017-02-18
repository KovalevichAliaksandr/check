package com.departments.controller;

import com.departments.model.Department;
import com.departments.model.Departments;
import com.departments.model.DepartmentsWithAvgSalary;
import com.departments.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.*;

/**
 * Created by alex on 9.2.17.
 */
@Controller
@RequestMapping(value = "/department")
public class DepartmentController {

    private static final Logger log= LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "/listDepartmentsWitAvgSalary",method = RequestMethod.GET)
    public String listDepartmentsWitAvgSalary(Model model){
        log.debug("start listDepartmentsWitAvgSalary");
        List<DepartmentsWithAvgSalary> listDepartmentsWitAvgSalary=departmentService.findDepartmentsWithAvgSalary();
        model.addAttribute("listDepartmentsWitAvgSalary",listDepartmentsWitAvgSalary);
        log.debug("size listDepartmentsWitAvgSalary is ={}",listDepartmentsWitAvgSalary.size());
        return  "department/listDepartmentsWitAvgSalary";
    }

    @RequestMapping(value = "/listDepartments",method = RequestMethod.GET)
    public String listDepartments(Model model){
        log.debug("start /listDepartments");
        List<Department> listDepartments=departmentService.findAllDepartments();
        model.addAttribute("listDepartments",listDepartments);
        log.debug("size listDepartments is ={}",listDepartments.size());
        return "department/listDepartments";
    }


    @RequestMapping(value = "/getDepartment/{id}",method = RequestMethod.GET)
    public String findContactById(@PathVariable Long id,Model model){
        log.debug("show department/{}",id);
        Department department=departmentService.findDepartmentById(id);
        model.addAttribute("department",department);
        log.debug("fetch department  ={}",department);
        return "department/showDepartment";
    }


    @RequestMapping(value = "/createDepartment",method = RequestMethod.POST)
    public String create (@RequestBody Department department,Model model){
        log.debug("Create department " , department);
        departmentService.save(department);
        model.addAttribute("department",department);
        log.debug("Department create successfully with info{}", department );
        return "department/createDepartment";
    }
//    @RequestMapping(value="/signup",method=RequestMethod.POST) public String doSignup(@Valid SignupForm form,Errors result,WebRequest request){
//        if (result.hasErrors()) {
//            return "signup";
//        }
//        WhirlwindUserDetails userDetails=WhirlwindUserDetails.createEnabledUser(form.getEmail(),form.getPassword());
//        if (exists(form)) {
//            result.rejectValue("email","accounts.emailAlreadyRegistered");
//        }
//        if (result.hasErrors()) {
//            return "signup";
//        }
//        try {
//            SignInUtils.signin(userDetails.getUsername());
//            ProviderSignInUtils.handlePostSignUp(userDetails.getUsername(),request);
//            saveUser(userDetails);
//            return "redirect:/";
//        }
//        catch (  DuplicateKeyException e) {
//            result.rejectValue("email","accounts.emailAlreadyRegistered");
//            return "signup";
//        }
//    }


    @ResponseBody
    @RequestMapping(value = "/createDepartments",method = RequestMethod.POST)
    public Departments departments(@RequestBody Departments departments){
        ArrayList<Department> departmentArrayList= departments.getDepartments();
        for (Department department:departmentArrayList){
            log.debug("Create departments " , department);
            departmentService.save(department);
            log.debug("Department create successfully with info{}", department );
        }
        return departments;
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
