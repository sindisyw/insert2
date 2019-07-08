/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.services;

import AssetManagement.AssetManagement.entities.Account;
import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.entities.Job;

import AssetManagement.AssetManagement.repository.JobRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class JobService {
    
    @Autowired
    private JobRepository jobRepository;
    
    public Iterable<Job> findAll() {
        return jobRepository.findAll();
    }
}
