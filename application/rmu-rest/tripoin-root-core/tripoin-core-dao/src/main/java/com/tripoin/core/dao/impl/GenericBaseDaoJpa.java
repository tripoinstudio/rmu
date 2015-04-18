package com.tripoin.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public <T> List<T> getObjectsUsingJQL(Class<T> objectType, String[] fields, Object[] values, Map<String, Object> orderMap) {

		String jqlQuery = "FROM " + objectType.getSimpleName() + " c ";
		int j = 0;
		if (fields != null && fields.length > 0) {
			for (int i = 0; i < fields.length; i++) {
				if (i == 0)
					jqlQuery += " WHERE c." + fields[i] + " = ?"+i;
				else
					jqlQuery += " AND c." + fields[i] + " = ?"+i;
			}
		}
		
		if (orderMap != null && orderMap.size() > 0) {
			for (Map.Entry<String, Object> entry : orderMap.entrySet()) {
				if (j == 0) {
					jqlQuery += " ORDER BY c." + entry.getKey() + " "
							+ entry.getValue() + " ";
					j++;
				} else {
					jqlQuery += " , c." + entry.getKey() + " "
							+ entry.getValue() + " ";
				}
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

	@Override
	public <T> List<T> getObjectsUsingJQL(Class<T> objectType, Map<String, Object> eqMap, Map<String, Object[]> betweenMap, Map<String, Object> orMap, Map<String, Object> orderMap) {
		// TODO Auto-generated method stub
		
		int i = 0;
		int j = 0;
		String hqlQuery = " FROM " + objectType.getSimpleName() + " c ";
		Map<Integer , Object> mapParam = new HashMap<Integer, Object>();

		if (eqMap != null && eqMap.size() > 0) {
			for (Map.Entry<String, Object> entry : eqMap.entrySet()) {
				if (i == 0) {
					hqlQuery += " WHERE c." + entry.getKey() + " = ?"+i;
					mapParam.put(i, entry.getValue());
					i++;
				} else {
					hqlQuery += " AND c." + entry.getKey() + " = ?"+i;
					mapParam.put(i, entry.getValue());
					i++;
				}
			}
		}

		if (betweenMap != null && betweenMap.size() > 0) {
			for (Map.Entry<String, Object[]> entry : betweenMap.entrySet()) {
				if (i == 0) {
					hqlQuery += " WHERE c." + entry.getKey()
							+ " BETWEEN ?"+i;
					mapParam.put(i, ((entry.getValue())[0]));
					i++;
					hqlQuery += " AND ?"+i;
					mapParam.put(i, ((entry.getValue())[1]));
					i++;
				} else {
					hqlQuery += " AND c." + entry.getKey()
							+ " BETWEEN ?"+i;
					mapParam.put(i, ((entry.getValue())[0]));
					i++;
					hqlQuery += " AND ?"+i;
					mapParam.put(i, ((entry.getValue())[1]));
					i++;
				}
			}
		}

		if (orMap != null && orMap.size() > 0) {
			if (i == 0)
				hqlQuery += " WHERE ( ";
			else
				hqlQuery += " AND ( ";

			j = 0;
			for (Map.Entry<String, Object> entry : orMap.entrySet()) {
				if (j == 0) {
					hqlQuery += " c." + entry.getKey() + " = ?"+i;
					mapParam.put(i, entry.getValue());
					i++;
				} else {
					hqlQuery += " OR c." + entry.getKey() + " = ?"+i;
					mapParam.put(i, entry.getValue());
					i++;
				}
			}
			hqlQuery += " ) ";
		}

		j = 0;
		if (orderMap != null && orderMap.size() > 0) {
			for (Map.Entry<String, Object> entry : orderMap.entrySet()) {
				if (j == 0) {
					hqlQuery += " ORDER BY c." + entry.getKey() + " "
							+ entry.getValue() + " ";
					j++;
				} else {
					hqlQuery += " , c." + entry.getKey() + " "
							+ entry.getValue() + " ";
				}
			}
		}
		
		Query query = getEntityManager().createQuery(hqlQuery);
		for (Map.Entry<Integer, Object> entry : mapParam.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<T> result = query.getResultList();	
		return result;
	}

	@Override
	public <T> List<T> getObjectsUsingManual(String hqlString, Object[] values, int first, int pageSize) {
		// TODO Auto-generated method stub
		
		Query query = getEntityManager().createQuery(hqlString);
		for (int i = 0 ; i < values.length ; i++) {
			query.setParameter(i, values[i]);
		}
		
		if(pageSize > 0){
			query.setFirstResult(first);
			query.setMaxResults(pageSize);
		}
		return query.getResultList();
	}

	@Override
	public <T> List<T> getObjectsUsingLike(Class<T> objectType, String field, String value) {
		// TODO Auto-generated method stub
//		String queryJQL = ;
		Query query = getEntityManager().createQuery("FROM " + objectType.getSimpleName() + " c WHERE c." + field + " LIKE '%"+value+"%'");
		return query.getResultList();
	}

}
