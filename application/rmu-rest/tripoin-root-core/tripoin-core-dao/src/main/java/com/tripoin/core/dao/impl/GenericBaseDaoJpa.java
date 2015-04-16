package com.tripoin.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tripoin.core.dao.base.ABaseDaoJpa;

@Repository
public class GenericBaseDaoJpa extends ABaseDaoJpa {

	@Override
	@Transactional
	public <T> T loadObject(Class<T> objectType, Serializable key) throws Exception {
		return getEntityManager().find(objectType, key);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception {
		Query query = getEntityManager().createQuery("select c from "+objectType.getName()+" c");
		List<T> resultList = query.getResultList();
	    return resultList;
	}

	@Override
	@Transactional
	public <T> List<T> loadObjectsFilterKey(Class<T> objectType, Serializable key) throws Exception {
		return null;
	}

}
