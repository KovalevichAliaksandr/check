package com.departments.web;

import com.departments.model.Department;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by alex on 3.3.17.
 */
public interface DepartmentWebControllerInterface {

    String listDepartmentsWitAvgSalary(Model model);
    String listDepartments(Model model);
    String findDepartmentById(@PathVariable Long id, Model model);
    String create(@Valid Department department, BindingResult bindingResult, Model model,
                  HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale);
    String createForm(Model model);
    String updateForm(@PathVariable("id") Long id, Model model);
    String update(@Valid Department department, BindingResult bindingResult, Model model,
                  HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes,
                  Locale locale);
    String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes, Locale locale);
    String deleteForm(@PathVariable("id") Long id, Model model);
}
