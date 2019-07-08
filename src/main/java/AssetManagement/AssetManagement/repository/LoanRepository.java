/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.repository;

import AssetManagement.AssetManagement.entities.Employee;
import AssetManagement.AssetManagement.entities.LoaningRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface LoanRepository extends CrudRepository<LoaningRequest, String>{
    @Query(value = "SELECT * FROM loaning_request  WHERE id =?1", nativeQuery = true)
    LoaningRequest getLoanById(String id);
}
