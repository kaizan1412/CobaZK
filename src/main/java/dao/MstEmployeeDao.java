package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.MstEmployee;
import entity.pk.MstEmployeePk;

public interface MstEmployeeDao extends 
	JpaRepository<MstEmployee, MstEmployeePk>{

	@Query(value="SELECT p FROM MstEmployee p, MstUser u WHERE p.username = u.username AND p.username = :username")
	public MstEmployee findByUsername(@Param("username") String username);
	
}
