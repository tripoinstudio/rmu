package com.tripoin.core.dao.base;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.tripoin.core.dao.IGenericDaoJpa;

public abstract class ABaseDaoJpa implements IGenericDaoJpa {

    @PersistenceContext
	@Qualifier(value="entityManagerFactory")
	private EntityManager entityManager;
	 
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
	@Override
	@Transactional
	public void saveObject(Object objectType) throws Exception {
		getEntityManager().persist(objectType);
		getEntityManager().flush();
	}

	@Override
	@Transactional
	public void updateObject(Class<?> objectType, Serializable key) throws Exception {
		getEntityManager().merge(objectType);
		getEntityManager().flush();
	}

	@Override
	@Transactional
	public void deleteObject(Class<?> objectType) throws Exception {
		getEntityManager().remove(objectType);
		getEntityManager().flush();
	}	
    
}