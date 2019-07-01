/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.services.AccountServices;
import AssetManagement.AssetManagement.services.AssetServices;
import AssetManagement.AssetManagement.services.EmployeeServices;
import AssetManagement.AssetManagement.services.JobServices;
import AssetManagement.AssetManagement.services.LoanServices;
import AssetManagement.AssetManagement.services.RepairServices;
import AssetManagement.AssetManagement.services.RoleServices;
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
    private EmployeeServices employeeServices;
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private JobServices jobServices;
    @Autowired
    private RoleServices roleServices;
    @Autowired
    private LoanServices loanServices;
    @Autowired
    private RepairServices repairServices;
    @Autowired
    private AssetServices assetServices;
    

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
        model.addAttribute("dataAcc", accountServices.findAll());
        return "employee";
    }

    @GetMapping("/job&role")
    public String job(Model model) {
        model.addAttribute("dataJob", jobServices.findAll());
        model.addAttribute("dataRole",roleServices.findAll());
        return "job";
    }
    
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
    @GetMapping("/asset")
    public String asset(Model model) {
        model.addAttribute("dataAsset", assetServices.findAll());
        return "asset";
    }

    

    @PostMapping("/addData")
    public String addData(Employee employee) {
        employee.setId("0");
        employee.setIsDelete("false");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/EmpController/softdelete/{id}")
    public String softDelete(@PathVariable("id") String id, Employee employee) {
        employee.setIsDelete("true");
        employeeRepository.save(employee);
        return "redirect:/employee";
    }
}
