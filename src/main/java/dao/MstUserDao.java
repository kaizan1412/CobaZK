package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dao.custom.MstUserCustomDao;
import entity.MstUser;
import entity.pk.MstUserPk;

public interface MstUserDao extends JpaRepository<MstUser, MstUserPk>, MstUserCustomDao{

	@Query(value="SELECT p FROM MstUser p WHERE p.username = :username and p.password = :password")
	public MstUser findByUsernamePassword(
			@Param("username") String username,
			@Param("password") String password);
	
}
