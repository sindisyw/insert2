/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.controllers;

import AssetManagement.AssetManagement.entities.Account;
import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.entities.EmployeeJob;
import AssetManagement.AssetManagement.entities.EmployeeRole;
import AssetManagement.AssetManagement.entities.Job;
import AssetManagement.AssetManagement.entities.Role;
import AssetManagement.AssetManagement.repository.AccountRepository;
import AssetManagement.AssetManagement.repository.EmployeeJobRepository;
import AssetManagement.AssetManagement.repository.JobRepository;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import AssetManagement.AssetManagement.repository.EmployeeRoleRepository;
import AssetManagement.AssetManagement.services.AccountService;
import AssetManagement.AssetManagement.services.AssetService;
import AssetManagement.AssetManagement.services.EmployeeJobService;
import AssetManagement.AssetManagement.services.JobService;
import AssetManagement.AssetManagement.services.LoanService;
import AssetManagement.AssetManagement.services.RepairService;
import AssetManagement.AssetManagement.services.RoleService;
import java.util.List;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class AdminController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeJobRepository employeeJobRepository;
    @Autowired
    private JobRepository jobRepository;
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
    private AssetService assetServices;

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/admin-employee")
    public String adm_employee(Model model) {
        model.addAttribute("dataEmp", employeeRepository.getAll());
        model.addAttribute("dataAcc", accountServices.findAll());
        model.addAttribute("dataEmpJob", employeeJobServices.findAll());
        model.addAttribute("dataRole", roleServices.findAll());
        model.addAttribute("dataJob", jobServices.findAll());

        return "admin/employee2";
    }

    @GetMapping("/admin-job&role")
    public String job(Model model) {
        model.addAttribute("dataJob", jobServices.findAll());
        model.addAttribute("dataRole", roleServices.findAll());
        return "admin/job";
    }

    @GetMapping("/admin-asset")
    public String asset(Model model) {
        model.addAttribute("dataAsset", assetServices.findAll());
        return "admin/asset";
    }

    @PostMapping("/addEmployee")
    public String addData(Employee employee) {
        employee.setId("0");
        employee.setIsDelete("false");
        employeeRepository.save(employee);
        return "redirect:/admin-employee";
    }

    @PostMapping("/addAcount")
    public String addAcc(String password, String id, String role, String job, Account account, EmployeeRole employeeRole, EmployeeJob employeeJob) {
        //account.setId("0");
        account.setIsDelete("false");
        account.setPassword(new BCryptPasswordEncoder().encode(password));
        account.setIsActive("false");
        employeeRole.setEmployee(new Employee(id));
        employeeRole.setRole(new Role(role));
        employeeJob.setEmployee(new Employee(id));
        employeeJob.setJob(new Job(job));
        accountRepository.save(account);
        employeeRoleRepository.save(employeeRole);
        employeeJobRepository.save(employeeJob);

        return "redirect:/admin-employee";
    }

//    @GetMapping("/findEmp")
//    @ResponseBody
//    public Employee findOne(String id) {
//        Employee e = new Employee(employeeRepository.getEmployeeById(id).get(0).getId(),
//                employeeRepository.getEmployeeById(id).get(0).getFirstName(),
//                employeeRepository.getEmployeeById(id).get(0).getLastName(),
//                employeeRepository.getEmployeeById(id).get(0).getEmail(),
//                employeeRepository.getEmployeeById(id).get(0).getSalary(),
//                employeeRepository.getEmployeeById(id).get(0).getPhoneNumber(),
//                employeeRepository.getEmployeeById(id).get(0).getManager().getId()
//        );
//        return e;
//    }
    @PostMapping("/employeeEdit/id")
    public String updateEmp(Employee employee) {
        employee.setIsDelete("false");
        employeeRepository.save(employee);
        return "redirect:/admin-employee";
    }
    @PostMapping("/deleteEmp/id")
    public String delEmp(Employee employee) {
        employee.setIsDelete("true");
        employeeRepository.save(employee);
        return "redirect:/admin-employee";
    }

//    @PostMapping("/deleteEmp/id")
//    public String softDeleteEmp(Employee employee) {
//        employee.setIsDelete("true");
//        employeeRepository.save(employee);
//        return "redirect:/admin-employee";
//    }

    @GetMapping("/findE")
    public String findById(String id) {
        employeeRepository.findById(id);
        return "redirect:/employee";
    }

//    @GetMapping("/EmpController/softdelete/{id}")
//    public String softDelete(@PathVariable("id") String id, Employee employee) {
//        employee.setIsdelete("true");
//        employeeRepositories.save(employee);
//        return "redirect:/employee/all";
//    }
    @GetMapping("/findEmp")
    @ResponseBody
    public Employee employee(String id) {
        Employee emp = new Employee(
                employeeRepository.getEmployeeById(id).getId(),
                employeeRepository.getEmployeeById(id).getFirstName(),
                employeeRepository.getEmployeeById(id).getLastName(),
                employeeRepository.getEmployeeById(id).getEmail(),
                employeeRepository.getEmployeeById(id).getSalary(),
                employeeRepository.getEmployeeById(id).getPhoneNumber(),
                employeeRepository.getEmployeeById(id).getManager().getFirstName()+' '+employeeRepository.getEmployeeById(id).getManager().getLastName()
        );
        return emp;
    }
    
    @GetMapping("/findEmp2")
    @ResponseBody
    public Employee employee1(String id) {
        Employee emp = employeeRepository.getEmployeeById(id);
        emp = new Employee(
                emp.getId(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getEmail(),
                emp.getSalary(),
                emp.getPhoneNumber(),
                emp.getManager().getId());
        return emp;
    }

}
