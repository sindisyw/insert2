/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.entities.LoaningRequest;
import AssetManagement.AssetManagement.entities.RepairRequest;
import AssetManagement.AssetManagement.entities.Status;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.repository.LoanRepository;
import AssetManagement.AssetManagement.repository.RepairRepository;
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
import javax.validation.Valid;
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
public class ManagerController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeJobService employeeJobServices;
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
    private RepairRepository repairRepository;
    @Autowired
    private AssetService assetServices;
    @Autowired
    private DetailAssetService detailassetServices;
    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/deptmanager")
    public String manager(Model model) {
        model.addAttribute("count", loanRepository.count());
        return "dashboard/manager";
    }

    @GetMapping("/manager_approval-request")
    public String approvalRequest(Model model) {
        model.addAttribute("dataLoaning", loanServices.findAll());
        model.addAttribute("dataEmp", employeeRepository.findAll());
        model.addAttribute("detailAsset", detailassetServices.findAll());
        return "manager/approval_request";
    }

    @GetMapping("/manager_approval-history")
    public String approvalHistory(Model model) {
        model.addAttribute("dataLoaning", loanServices.findAll());
        return "manager/approval_history";
    }

    @GetMapping("/manager_repair")
    public String repair(Model model) {
        model.addAttribute("dataRepair", repairServices.findAll());
        model.addAttribute("detailAsset", detailassetServices.findAll());
        model.addAttribute("dataEmp", employeeRepository.findAll());

        return "manager/repair";
    }

    @PostMapping("/approveLoaning/{id}")
    public String upadateData(@PathVariable("id") String id, @Valid Employee employee) {
        employee.setIsDelete("false");
        employeeRepository.save(employee);
        return "redirect:/employee/all";
    }

    @PostMapping("/addRepairRequest")
    public String addData(RepairRequest repair) {
        repair.setId("0");
        repair.setIsDelete("false");
        Status status = new Status();
        status.setId("ST1");
        repair.setStatus(status);
        repairRepository.save(repair);
        return "redirect:/manager_repair";
    }
    @PostMapping("/cancelRequest/id")
    public String cancelReq(RepairRequest repair) {
        repair.setIsDelete("false");
        Status status = new Status();
        status.setId("ST4");
        repair.setStatus(status);
        repairRepository.save(repair);
        return "redirect:/manager_repair";
    }

    @GetMapping("/findLoaning")
    @ResponseBody
    public LoaningRequest loanReq(String id) {
        LoaningRequest req = loanRepository.getLoanById(id);
        req = new LoaningRequest(
                req.getId(),
                req.getLoaningDate(),
                req.getReturnDate(),
                req.getLoaningTotal(),
                req.getNote(),
                req.getQuantity(),
                req.getDetailAsset().getId(),
                req.getEmployee().getId()
        );
        return req;
    }
    
    @PostMapping("/approveReq/id")
    public String approveReq(LoaningRequest loaningRequest) {
        loaningRequest.setIsDelete("false");
        Status status = new Status();
        status.setId("ST2");
        loaningRequest.setStatus(status);
        loanRepository.save(loaningRequest);
        return "redirect:/manager_approval-request";
    }
    @PostMapping("/rejectReq/id")
    public String rejectReq(LoaningRequest loaningRequest) {
        loaningRequest.setIsDelete("false");
        Status status = new Status();
        status.setId("ST3");
        loaningRequest.setStatus(status);
        loanRepository.save(loaningRequest);
        return "redirect:/manager_approval-request";
    }

    @GetMapping("/findRepair")
    @ResponseBody
    public RepairRequest repairReq(String id) {
        RepairRequest rep= repairRepository.getRepairById(id);
        rep = new RepairRequest(
                rep.getId(),
                rep.getNote(),
                rep.getQuantity(),
                rep.getDetailAsset().getId(),
                rep.getEmployee().getId());
        return rep;
    }
}
