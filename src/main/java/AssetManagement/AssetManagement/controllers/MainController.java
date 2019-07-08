/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.services.AccountService;
import AssetManagement.AssetManagement.services.AssetService;
import AssetManagement.AssetManagement.services.EmployeeJobService;
import AssetManagement.AssetManagement.services.JobService;
import AssetManagement.AssetManagement.services.LoanService;
import AssetManagement.AssetManagement.services.RepairService;
import AssetManagement.AssetManagement.services.RoleService;
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
 * @author HP
 */
@Controller
public class MainController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeJobService employeeServices;
    @Autowired
    private AccountService accountServices;
    @Autowired
    private JobService jobServices;
    @Autowired
    private RoleService roleServices;
    @Autowired
    private LoanService loanServices;
    @Autowired
    private RepairService repairServices;
    @Autowired
    private AssetService assetServices;

    @RequestMapping(value = {"/", "", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    
    @GetMapping("/home")
    public String index(Model model) {
        return "dashboard/home";
    }

//    @GetMapping("/login")
//    public String login(Model model) {
//        return "login";
//    }

    @GetMapping("/request")
    public String loaning(Model model) {
        model.addAttribute("dataLoaning", loanServices.findAll());
        model.addAttribute("dataRepair", repairServices.findAll());
        return "request";
    }

    @GetMapping("/history")
    public String history(Model model) {
        model.addAttribute("dataLoaning", loanServices.findAll());
        model.addAttribute("dataRepair", repairServices.findAll());
        return "history";
    }

  
    @GetMapping("/EmpController/softdelete/{id}")
    public String softDelete(@PathVariable("id") String id, Employee employee) {
        employee.setIsDelete("true");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }
}
