/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.services;



import AssetManagement.AssetManagement.entities.RepairRequest;
import AssetManagement.AssetManagement.repository.RepairRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class RepairService {
    
    @Autowired
    private RepairRepository repairRepository;
    
    public Iterable<RepairRequest> findAll() {
        return repairRepository.findAll();
    }
}
