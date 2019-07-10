/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.entities.Status;
import AssetManagement.AssetManagement.entities.LoaningRequest;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.repository.LoanRepository;
import AssetManagement.AssetManagement.services.AccountService;
import AssetManagement.AssetManagement.services.AssetService;
import AssetManagement.AssetManagement.services.DetailAssetService;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author HP
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeRepository employeeServices;
    @Autowired
    private AccountService accountServices;
    @Autowired
    private JobService jobServices;
    @Autowired
    private RoleService roleServices;
    @Autowired
    private LoanService loanServices;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private RepairService repairServices;
    @Autowired
    private AssetService assetServices;
    @Autowired
    private DetailAssetService detailassetServices;

    @GetMapping("/employee")
    public String manager(Model model) {
        return "dashboard/employee";
    }

    @GetMapping("/emp_loaning")
    public String approvalRequest(Model model) {
        model.addAttribute("dataEmp", employeeServices.findAll());
        model.addAttribute("dataLoaning", loanServices.findAll());
        model.addAttribute("dataAsset", assetServices.findAll());
        model.addAttribute("detailAsset", detailassetServices.findAll());
        return "employee/loaning";
    }

    @PostMapping("/loaningReq/addData")
    public String addLoan(LoaningRequest loan) {
        loan.setId("0");
        loan.setIsDelete("false");
        Status status = new Status();
        status.setId("ST1");
        loan.setStatus(status);
        loanRepository.save(loan);
        return "redirect:/emp_loaning";
    }
}
