/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.repository;

import AssetManagement.AssetManagement.entities.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, String>{
    @Query(value = "UPDATE account SET is_delete = 'true' WHERE id =?1", nativeQuery = true)
    Account accountSoftDelete(String id);
}
