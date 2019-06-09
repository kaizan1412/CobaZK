package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
