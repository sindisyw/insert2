/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.services;


import AssetManagement.AssetManagement.entities.Role;

import AssetManagement.AssetManagement.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }
}
