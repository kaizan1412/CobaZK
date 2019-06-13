package dao.impl;

import java.sql.CallableStatement;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.jdbc.object.StoredProcedure;

import dao.custom.MstUserCustomDao;
import entity.MstUser;

public class MstUserDaoImpl implements MstUserCustomDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MstUser getLastInserted() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<MstUser>> findAllBySpFirstWay() {
		StoredProcedureQuery spQuery =  entityManager.createStoredProcedureQuery("sp_userfindall", MstUser.class);
		
		try {
			spQuery.execute();
			
			List<MstUser> users = spQuery.getResultList();
			
			if(users != null && !users.isEmpty() && users.size() > 0){
				return Optional.of(users);
			}
			else{
				return Optional.empty();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
		
	}

	@Override
	public Optional<List<MstUser>> findAllBySpSecondWay() {
		
		Query query =  entityManager.createNativeQuery("{call sp_userfindall()}", MstUser.class);
		
		try {
			
			List<MstUser> users = query.getResultList();
			
			if(users != null && !users.isEmpty() && users.size() > 0){
				return Optional.of(users);
			}
			else{
				return Optional.empty();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<MstUser>> findAllBySpParamFirstWay(String username, String password) {
		StoredProcedureQuery spQuery =  entityManager.createStoredProcedureQuery("sp_userfindbyuserpass", MstUser.class);
		spQuery.registerStoredProcedureParameter("userName", String.class, ParameterMode.IN);
		spQuery.registerStoredProcedureParameter("password", String.class, ParameterMode.IN);
		spQuery.setParameter("userName",username);
		spQuery.setParameter("password", password);
		
		try {
			spQuery.execute();
			
			List<MstUser> users = spQuery.getResultList();
			
			if(users != null && !users.isEmpty() && users.size() > 0){
				return Optional.of(users);
			}
			else{
				return Optional.empty();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<MstUser>> findAllBySpParamSecondWay(String username,
			String password) {
		StoredProcedureQuery spQuery =  entityManager.createStoredProcedureQuery("sp_userfindbyuserpass", MstUser.class);
		spQuery.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		spQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		spQuery.setParameter(1,username);
		spQuery.setParameter(2, password);
		
		try {
			spQuery.execute();
			
			List<MstUser> users = spQuery.getResultList();
			
			if(users != null && !users.isEmpty() && users.size() > 0){
				return Optional.of(users);
			}
			else{
				return Optional.empty();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<MstUser>> findAllBySpParamThirdWay(String username,
			String password) {
		Query query =  entityManager.createNativeQuery("{call sp_userfindbyuserpass(?, ?)}", MstUser.class);
		query.setParameter(1, username);
		query.setParameter(2, password);
		try {
			
			List<MstUser> users = query.getResultList();
			
			if(users != null && !users.isEmpty() && users.size() > 0){
				return Optional.of(users);
			}
			else{
				return Optional.empty();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	@Override
	public Optional<List<MstUser>> findAllBySpParamFourthWay(String username,
			String password) {
		Query query =  entityManager.createNativeQuery("{call sp_userfindbyuserpass(:username, :password)}", MstUser.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
			
			List<MstUser> users = query.getResultList();
			
			if(users != null && !users.isEmpty() && users.size() > 0){
				return Optional.of(users);
			}
			else{
				return Optional.empty();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
