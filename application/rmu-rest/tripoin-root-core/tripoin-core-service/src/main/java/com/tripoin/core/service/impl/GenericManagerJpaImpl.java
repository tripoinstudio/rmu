package com.tripoin.core.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dao.IGenericDaoJpa;
import com.tripoin.core.service.IGenericManagerJpa;

@Service
public class GenericManagerJpaImpl implements IGenericManagerJpa {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericManagerJpaImpl.class);
	
	protected Map<String, Object> eqMap;
    protected Map<String, Object> notEqMap;
    protected Map<String, Object[]> betweenMap;
    protected Map<String, Object[]> notBetweenMap;
    protected Map<String, Object[]> inMap;
    protected Map<String, Object[]> notInMap;
    protected Map<String, Object> likeMap;
    protected Map<String, Object> notLikeMap;
    protected Map<String, Object> orderMap;
    protected Map<String, Object> orMap;
    protected Criterion orCriterion;
    
    public void resetMap(){
    	eqMap = null;
        notEqMap = null;
        betweenMap = null;
        notBetweenMap = null;
        inMap = null;
        notInMap = null;
        likeMap = null;
        notLikeMap = null;
        orderMap = null;
        orMap = null;
        orCriterion = null;
        
        eqMap = new HashMap<String, Object>();
		betweenMap = new HashMap<String, Object[]>();
		orMap = new HashMap<String, Object>();
		orderMap = new LinkedHashMap<String, Object>();
    }

	@Autowired
	private IGenericDaoJpa genericDao;
	
	@Override
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception {
		LOGGER.debug("GenericManagerRPCJpa - Class name : "+objectType.getName());
		List<T> objects = genericDao.loadObjects(objectType);
		return objects;
	}
	
	@Override
	public void saveObject(Object objectType) throws Exception {
		LOGGER.debug("GenericManagerRPCJpa - Class name : "+objectType.toString());
		genericDao.saveObject(objectType);	
	}

	@Override
	public <T> List<T> getObjectsUsingParameter(Class<T> objectType, String[] fields, Object[] values, Map<String, Object> orderMap) {
		// TODO Auto-generated method stub
		return genericDao.getObjectsUsingJQL(objectType, fields, values, orderMap);
	}

	@Override
	public <T> List<T> getObjectsUsingParameter(Class<T> objectType, String[] eqList, String[] orList, String[] betweenList, Object[] values, String orderBy, String order) {
		// TODO Auto-generated method stub
		resetMap();
		int j = 0;
		if (eqList != null && eqList.length > 0) {
			for (int i = 0; i < eqList.length; i++) {
				eqMap.put(eqList[i], values[j]);
				j++;
			}
		}
		
		if (orList != null && orList.length > 0) {
			for (int i = 0; i < orList.length; i++) {
				orMap.put(orList[i], values[j]);
				j++;
			}
		}
		
		if (betweenList != null && betweenList.length > 0) {
			for (int i = 0; i < betweenList.length; i++) {
				betweenMap.put(betweenList[i], new Object[]{values[j],values[j++]});
				j++;
			}
		}
		
		if(orderBy != null && !orderBy.trim().equalsIgnoreCase(""))
			orderMap.put(orderBy, order);
		
		return genericDao.getObjectsUsingJQL(objectType, eqMap, betweenMap, orMap, orderMap);
	}

	@Override
	public <T> List<T> getObjectsUsingParameterManualPage(String hqlString, Object[] values, int first, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.getObjectsUsingManual(hqlString, values, first, pageSize);
	}

	@Override
	public <T> List<T> getObjectsUsingParameterLike(Class<T> objectType,
			String field, String value) {
		// TODO Auto-generated method stub
		return genericDao.getObjectsUsingLike(objectType, field, value);
	}

	
	
}
