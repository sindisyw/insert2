/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.repository;
import AssetManagement.AssetManagement.entities.LoaningRequest;
import AssetManagement.AssetManagement.entities.RepairRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface RepairRepository extends CrudRepository<RepairRequest, String>{
    @Query(value = "SELECT * FROM repair_request  WHERE id =?1", nativeQuery = true)
    RepairRequest getRepairById(String id);
}
