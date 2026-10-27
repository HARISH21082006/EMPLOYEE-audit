package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employee-list";
    }

    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("employee")) {
            model.addAttribute("employee", new Employee());
        }
        return "employee-form";
    }

    @PostMapping
    public String registerEmployee(@Valid @ModelAttribute("employee") Employee employee,
                                   BindingResult bindingResult,
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "Register Employee");
            return "employee-form";
        }
        try {
            employeeService.registerEmployee(employee);
            redirectAttributes.addFlashAttribute("successMessage", "Employee registered successfully.");
            return "redirect:/employees";
        } catch (RuntimeException ex) {
            model.addAttribute("pageTitle", "Register Employee");
            model.addAttribute("formError", "The employee could not be saved. Please try again.");
            return "employee-form";
        }
    }
}
