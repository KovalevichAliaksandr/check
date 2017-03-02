package com.departments.web;




import com.departments.model.Department;
import com.departments.model.Employee;
import com.departments.model.EmployeeWithDepartment;
import com.departments.web.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by alex on 1.3.17.
 */
@Controller
@RequestMapping(value = "/employee")
public class EmployeeWebController {
    private static final Logger log = LoggerFactory.getLogger(DepartmentWebController.class);

    public static final String URL_GET_LIST_EMPLOYEES = "http://localhost:8080/rest/employee/listEmployees";
    public static final String URL_GET_LIST_EMPLOYEES_WITH_DEPARTMENTS = "http://localhost:8080/rest/employee/listEmployeesWithDepartments";
    public static final String URL_GET_EMPLOYEE_BY_ID = "http://localhost:8080/rest/employee/getEmployee/{id}";
    public static final String URL_GET_EMPLOYEE_WITH_DEPARTMENT_BY_ID = "http://localhost:8080/rest/employee/getEmployeeWithDepartment/{id}";
    public static final String URL_CREATE_EMPLOYEE = "http://localhost:8080/rest/employee/createEmployee";
    public static final String URL_UPDATE_EMPLOYEE_BY_ID = "http://localhost:8080/rest/employee/updateEmployee/{id}";
    public static final String URL_DELETE_EMPLOYEE_BY_ID = "http://localhost:8080/rest/employee/deleteEmployee/{id}";
    public static final String URL_GET_LIST_DEPARTMENTS = "http://localhost:8080/rest/department/listDepartments";


    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @RequestMapping(value = "/listEmployees", method = RequestMethod.GET)
    public String listEmployees(Model model) {
        log.debug("start listEmployees");
        RestTemplate restTemplate = new RestTemplate();
        List<Employee> listEmployees =
                restTemplate.getForObject(URL_GET_LIST_EMPLOYEES, List.class);
        model.addAttribute("listEmployees", listEmployees);
        log.debug("size listEmployees is ={}", listEmployees.size());
        return "employee/listEmployees";
    }

    @RequestMapping(value = "/listEmployeesWithDepartments", method = RequestMethod.GET)
    public String listEmployeesWithDepartments(Model model) {
        log.debug("start listEmployeesWithDepartments");
        RestTemplate restTemplate = new RestTemplate();
        List<EmployeeWithDepartment> listEmployeesWithDepartments =
                restTemplate.getForObject(URL_GET_LIST_EMPLOYEES_WITH_DEPARTMENTS, List.class);
        model.addAttribute("listEmployeesWithDepartments", listEmployeesWithDepartments);
        log.debug("size listEmployees is ={}", listEmployeesWithDepartments.size());
        return "employee/listEmployeesWithDepartments";
    }

    @RequestMapping(value = "/showEmployee/{id}", method = RequestMethod.GET)
    public String findContactById(@PathVariable Long id, Model model) {
        log.debug("show employee{}", id);
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        EmployeeWithDepartment employeeWithDepartment =
                restTemplate.getForObject(URL_GET_EMPLOYEE_WITH_DEPARTMENT_BY_ID, EmployeeWithDepartment.class, params);
        model.addAttribute("employeeWithDepartment", employeeWithDepartment);
        log.debug("fetch employeeWithDepartment  ={}", employeeWithDepartment);
        return "employee/showEmployee";
    }
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
    public String create(@Valid Employee employee, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {

        RestTemplate restTemplate = new RestTemplate();
        List<Department> listDepartments;
        ResponseEntity<Department[]> responseEntity;

        log.debug("Create employee ", employee);

        if (bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",
                    messageSource.getMessage("employee_save_fail", new Object[]{}, locale)));
            responseEntity = restTemplate.getForEntity(URL_GET_LIST_DEPARTMENTS, Department[].class);
            listDepartments = Arrays.asList(responseEntity.getBody());
            model.addAttribute("listDepartments",listDepartments);
            model.addAttribute("employee", employee);
            return "employee/createEmployee";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Employee> requestCreate = new HttpEntity<>(employee, headers);
        try {
            restTemplate.exchange(URL_CREATE_EMPLOYEE, HttpMethod.POST, requestCreate, Employee.class);
            redirectAttributes.addFlashAttribute("message", new Message("success",
                    messageSource.getMessage("employee_save_success", new Object[]{}, locale)));
            log.debug("Department create successfully with info {}", employee);
            return "redirect:/employee/listEmployeesWithDepartments";
        } catch (RuntimeException e) {
            responseEntity = restTemplate.getForEntity(URL_GET_LIST_DEPARTMENTS, Department[].class);
            listDepartments = Arrays.asList(responseEntity.getBody());
            model.addAttribute("listDepartments",listDepartments);
            model.addAttribute("message", new Message("error",
                    messageSource.getMessage("employee_save_fail_not_department", new Object[]{}, locale)));
            return "employee/createEmployee";
        }
    }

    @RequestMapping(value = "/createEmployee", params = "formCreate", method = RequestMethod.GET)
    public String createForm(Model model) {
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Department[]> responseEntity = restTemplate.getForEntity(URL_GET_LIST_DEPARTMENTS, Department[].class);
        List<Department> listDepartments = Arrays.asList(responseEntity.getBody());
        model.addAttribute("listDepartments",listDepartments);
        model.addAttribute("employee", new Employee());
        return "employee/createEmployee";
    }

    @RequestMapping(value = "/updateEmployee/{id}", params = "formUpdate", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        Employee employee = restTemplate.getForObject(URL_GET_EMPLOYEE_BY_ID, Employee.class, params);
        model.addAttribute("employee", employee);
        return "employee/updateEmployee";
    }

    @RequestMapping(value = "/updateEmployee/{id}", params = "formUpdate", method = RequestMethod.POST)
    public String update(@Valid Employee employee, BindingResult bindingResult, Model model,
                         HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                         Locale locale) {
        log.info("Updating employee");
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", new Message("error",
                    messageSource.getMessage("employee_save_fail", new Object[]{}, locale)));
            model.addAttribute("employee", employee);
            return "employee/updateEmployee";
        }
//        model.asMap().clear();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<Employee> requestUpdate = new HttpEntity<>(employee, headers);
        try {
            restTemplate.exchange(URL_UPDATE_EMPLOYEE_BY_ID, HttpMethod.PUT, requestUpdate,
                    Employee.class, employee.getId());
            redirectAttributes.addFlashAttribute("message", new Message("success",
                    messageSource.getMessage("employee_update_success", new Object[]{}, locale)));
            return "redirect:/employee/listEmployees";
        } catch (Exception e) {
            model.addAttribute("message", new Message("error",
                    messageSource.getMessage("employee_already_exists", new Object[]{}, locale)));
            return "employee/updateEmployee";
        }
    }
    @RequestMapping(value = "/deleteEmployee/{id}", params = "formDelete", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, Locale locale) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        Employee employee = restTemplate.getForObject(URL_GET_EMPLOYEE_BY_ID, Employee.class, params);
        log.debug("Delete employee {}", employee);
        try {
            restTemplate.delete(URL_DELETE_EMPLOYEE_BY_ID, params);
            log.debug("Delete employee successfully {}", employee);
            redirectAttributes.addFlashAttribute("message", new Message("success",
                    messageSource.getMessage("employee_delete_success", new Object[]{}, locale)));
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", new Message("error",
                    messageSource.getMessage("employee_can_not_be_removed", new Object[]{}, locale)));
        }
        return "redirect:/department/listEmployees";
    }

    @RequestMapping(value = "/deleteEmployee/{id}", params = "formDelete", method = RequestMethod.GET)
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        Employee employee = restTemplate.getForObject(URL_GET_EMPLOYEE_BY_ID, Employee.class, params);
        model.addAttribute("employee", employee);
        return "/employee/deleteEmployee";
    }

}
