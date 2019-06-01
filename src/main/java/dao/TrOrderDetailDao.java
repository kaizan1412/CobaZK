package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dto.TrOrderDetailDto;
import entity.TrOrderDetail;
import entity.pk.TrOrderDetailPk;

public interface TrOrderDetailDao extends JpaRepository<TrOrderDetail, TrOrderDetailPk>{
	
	@Query(value="SELECT p FROM TrOrderDetail p WHERE p.deleted = false")
	public List<TrOrderDetail> findAllDeletedFalse();
	
	@Query(value="SELECT p FROM TrOrderDetail p WHERE p.noNota = :noNota")
	public List<TrOrderDetail> findByNoNota(@Param("noNota") String noNota);

}
