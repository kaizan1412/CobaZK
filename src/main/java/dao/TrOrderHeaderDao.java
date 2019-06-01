package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.TrOrderHeader;
import entity.pk.TrOrderHeaderPk;

public interface TrOrderHeaderDao extends JpaRepository<TrOrderHeader, TrOrderHeaderPk>{
	
	@Query(value="SELECT p FROM TrOrderHeader p WHERE p.deleted = false")
	public List<TrOrderHeader> findAllDeletedFalse();
	
//	@Query(value="SELECT p FROM TrOrderHeader p WHERE p.noNota = :noNota")
//	public TrOrderHeader findByNoNota(@Param("noNota") String noNota);

}
