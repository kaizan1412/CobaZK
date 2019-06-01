package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.MstCustomer;
import entity.pk.MstCustomerPk;

public interface MstCustomerDao extends JpaRepository<MstCustomer, MstCustomerPk>{
	
	@Query(value="SELECT p FROM MstCustomer p WHERE p.deleted = false")
	public List<MstCustomer> findAllDeletedFalse();

}
