/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.services;


import AssetManagement.AssetManagement.entities.Asset;
import AssetManagement.AssetManagement.repository.AssetRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class AssetServices {
    
    @Autowired
    private AssetRepository assetRepository;
    
    public Iterable<Asset> findAll() {
        return assetRepository.findAll();
    }
}
