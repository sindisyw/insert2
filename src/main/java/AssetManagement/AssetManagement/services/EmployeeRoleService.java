/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.services;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.entities.EmployeeJob;
import AssetManagement.AssetManagement.repository.EmployeeJobRepository;
import AssetManagement.AssetManagement.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class EmployeeRoleService {
    
    @Autowired
    private EmployeeJobRepository employeeJobRepository;
    
    public Iterable<EmployeeJob> findAll() {
        return employeeJobRepository.findAll();
    }
}
