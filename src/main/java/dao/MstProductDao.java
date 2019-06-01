package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.MstProduct;
import entity.pk.MstProductPk;

public interface MstProductDao extends JpaRepository<MstProduct, MstProductPk>{
	
	@Query(value="SELECT p FROM MstProduct p WHERE p.deleted = false")
	public List<MstProduct> findAllDeleted();

}
