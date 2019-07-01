/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.services.AccountServices;
import AssetManagement.AssetManagement.services.EmployeeServices;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Okala
 */
@Controller
public class MainController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeServices employeeServices;
    @Autowired
    private AccountServices accountServices;

    @GetMapping("/")
    public String index(Model model) {
        return "index_Copy";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/employee")
    public String index2(Model model) {
        model.addAttribute("dataEmp", employeeRepository.getAll());

        return "employee";
    }

    @GetMapping("/job&role")
    public String job(Model model) {
        model.addAttribute("dataEmp", employeeRepository.getAll());

        return "job";
    }

    @PostMapping("/addData")
    public String addData(Employee employee) {
        employee.setId("0");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/EmpController/softdelete/{id}")
    public String softDelete(@PathVariable("id") String id, Employee employee) {
        employee.setIsDelete(true);
        employeeRepository.save(employee);
        return "redirect:/employee";
    }
}
