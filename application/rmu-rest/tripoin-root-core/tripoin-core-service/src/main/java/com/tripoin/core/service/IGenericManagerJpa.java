package com.tripoin.core.service;

import java.util.List;
import java.util.Map;

public interface IGenericManagerJpa {
	
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception;	
	
	public void saveObject(Object objectType) throws Exception;
	
	public <T> List<T> getObjectsUsingParameter(Class<T> objectType, String[] fields, Object[] values, Map<String, Object> orderMap);
	
	public <T> List<T> getObjectsUsingParameter(Class<T> objectType, String[] eqList, String[] orList, String[] betweenList, Object[] values, String orderBy, String order);
	
	public <T> List<T> getObjectsUsingParameterManualPage(String hqlString, Object[] values, int first, int pageSize);
	
	public <T> List<T> getObjectsUsingParameterLike(Class<T> objectType, String field, String value);
}
