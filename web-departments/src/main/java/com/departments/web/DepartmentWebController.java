package com.departments.web;

//        import com.departments.web.message.Message;

import com.departments.model.Department;
import com.departments.model.DepartmentsWithAvgSalary;
import com.departments.web.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by alex on 9.2.17.
 */
@Controller
@RequestMapping(value = "/department")
public class DepartmentWebController {
    private static final Logger log = LoggerFactory.getLogger(DepartmentWebController.class);

    public static final String URL_GET_LIST_DEPARTMENTS = "http://localhost:8080/rest/department/listDepartments";
    public static final String URL_GET_LIST_DEPARTMENTS_WIT_AVG_SALARY = "http://localhost:8080/rest/department/listDepartmentsWitAvgSalary";
    public static final String URL_GET_DEPARTMENT_BY_ID = "http://localhost:8080/rest/department/getDepartment/{id}";
    public static final String URL_CREATE_DEPARTMENT = "http://localhost:8080/rest/department/createDepartment";
    public static final String URL_UPDATE_DEPARTMENT_BY_ID = "http://localhost:8080/rest/department/updateDepartment/{id}";
    public static final String URL_DELETE_DEPARTMENT_BY_ID = "http://localhost:8080/rest/department/deleteDepartment/{id}";


    private MessageSource messageSource;


    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource=messageSource;
    }


    @RequestMapping(value = "/listDepartmentsWitAvgSalary", method = RequestMethod.GET)
    public String listDepartmentsWitAvgSalary(Model model) {
        log.debug("start listDepartmentsWitAvgSalary");
        RestTemplate restTemplate = new RestTemplate();
        List<DepartmentsWithAvgSalary> listDepartmentsWitAvgSalary = restTemplate.getForObject(URL_GET_LIST_DEPARTMENTS_WIT_AVG_SALARY, List.class);
        model.addAttribute("listDepartmentsWitAvgSalary", listDepartmentsWitAvgSalary);
        log.debug("size listDepartmentsWitAvgSalary is ={}", listDepartmentsWitAvgSalary.size());
        return "department/listDepartmentsWitAvgSalary";
    }

    @RequestMapping(value = "/listDepartments",method = RequestMethod.GET)
    public String listDepartments(Model model){
        log.debug("start /listDepartments");
        RestTemplate restTemplate = new RestTemplate();
        List<Department> listDepartments = restTemplate.getForObject(URL_GET_LIST_DEPARTMENTS, List.class);
        model.addAttribute("listDepartments",listDepartments);
        log.debug("size listDepartments is ={}",listDepartments.size());
        return "department/listDepartments";
    }

    @RequestMapping(value = "/showDepartment/{id}",method = RequestMethod.GET)
    public String findContactById(@PathVariable Long id, Model model){
        log.debug("show department/{}",id);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        Department department = restTemplate.getForObject(URL_GET_DEPARTMENT_BY_ID, Department.class,params);
        model.addAttribute("department",department);
        log.debug("fetch department  ={}",department);
        return "department/showDepartment";
    }

    @RequestMapping(value = "/createDepartment",method = RequestMethod.POST)
    public String create (@Valid Department department, BindingResult bindingResult, Model model,
                          HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
        log.debug("Create department " , department);
        if(bindingResult.hasErrors()){
            model.addAttribute("message",new Message("error",messageSource.getMessage("department_save_fail",new Object[]{},locale)));
            model.addAttribute("department",department);
            return "department/createDepartment";
        }
        try {

            redirectAttributes.addFlashAttribute("message", new Message("success",
                    messageSource.getMessage("department_save_success", new Object[]{}, locale)));
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<Department> requestCreate = new HttpEntity<>(department, headers);
            restTemplate.exchange(URL_CREATE_DEPARTMENT, HttpMethod.POST, requestCreate, Department.class);


//            model.addAttribute("department",department);
            log.debug("Department create successfully with info {}", department );
            return "redirect:/department/listDepartmentsWitAvgSalary" ;
        } catch (RuntimeException e) {
            model.addAttribute("message",new Message("error",messageSource.getMessage("department_already_exists",new Object[]{},locale)));
            return "department/createDepartment";
        }
    }

    @RequestMapping(value = "/createDepartment",params = "formCreate",method = RequestMethod.GET)
    public String createForm(Model model){
        Department department=new Department();
        model.addAttribute("department",department);
        return "department/createDepartment";
    }

    @RequestMapping(value = "/updateDepartment/{id}",params = "formUpdate", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        Department department = restTemplate.getForObject(URL_GET_DEPARTMENT_BY_ID, Department.class,params);
        model.addAttribute("department",department);
        return "department/updateDepartment";
    }


    @RequestMapping(value = "/updateDepartment/{id}", params = "formUpdate", method = RequestMethod.POST)
    public String update(@Valid Department department, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        log.info("Updating department");
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",
                    messageSource.getMessage("department_save_fail", new Object[]{}, locale)));
            model.addAttribute("department", department);
            return "department/updateDepartment";
        }
//        model.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success",
                messageSource.getMessage("department_save_success", new Object[]{}, locale)));
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Department> requestUpdate = new HttpEntity<>(department, headers);
        restTemplate.exchange(URL_UPDATE_DEPARTMENT_BY_ID, HttpMethod.PUT, requestUpdate, Department.class,department.getId());
        return "redirect:/department/listDepartmentsWitAvgSalary" ;

    }


    @RequestMapping(value = "/deleteDepartment/{id}",params = "formDelete",method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id,Model model,RedirectAttributes redirectAttributes,Locale locale){
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Long> params = new HashMap<String, Long>();
            params.put("id", id);
            Department department=restTemplate.getForObject(URL_GET_DEPARTMENT_BY_ID, Department.class,params);
            log.debug("Delete department {}",department);
            restTemplate.delete(URL_DELETE_DEPARTMENT_BY_ID,  params );
            log.debug("Delete department successfully {}",department);
            redirectAttributes.addFlashAttribute("message", new Message("success",
                    messageSource.getMessage("department_delete_success", new Object[]{}, locale)));

        } catch (RuntimeException e){
            redirectAttributes.addFlashAttribute("message",new Message("error",messageSource.getMessage("department_can_not_be_removed",new Object[]{},locale)));
        }
        return "redirect:/department/listDepartmentsWitAvgSalary" ;

    }

    @RequestMapping(value = "/deleteDepartment/{id}",params = "formDelete",method = RequestMethod.GET)
    public String deleteForm(@PathVariable("id")  Long id, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        Department department=restTemplate.getForObject(URL_GET_DEPARTMENT_BY_ID, Department.class,params);
        model.addAttribute("department",department);
        return "/department/deleteDepartment";
    }



}