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

	@Override
	public <T> List<T> getObjectsUsingJQL(Class<T> objectType, String[] fields, Object[] values) {

		String jqlQuery = "FROM " + objectType.getSimpleName() + " c ";

		if (fields != null && fields.length > 0) {
			for (int i = 0; i < fields.length; i++) {
				if (i == 0)
					jqlQuery += " WHERE c." + fields[i] + " = ?"+i;
				else
					jqlQuery += " AND c." + fields[i] + " = ?"+i;
			}
		}
		Query query = getEntityManager().createQuery(jqlQuery);
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		
		List<T> result = query.getResultList();
		return result;
	}

}
