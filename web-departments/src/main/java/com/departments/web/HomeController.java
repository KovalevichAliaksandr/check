package com.departments.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by alex on 3.3.17.
 */

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String homePage() {
        return "redirect:/department/listDepartmentsWitAvgSalary";
    }
}
