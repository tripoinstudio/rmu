package com.tripoin.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericDaoJpa {
	public <T> T loadObject(Class<T> objectType, Serializable key) throws Exception ;
	
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception;
	
	public <T> List<T> loadObjectsFilterKey(Class<T> objectType, Serializable key) throws Exception;
	
	public void saveObject(Object objectType) throws Exception;
	
	public void updateObject(Object objectType) throws Exception;
	
	public void deleteObject(Object objectType) throws Exception;
	
	public <T> List<T> getObjectsUsingJQL(Class<T> objectType, String[] fields, Object[] values, Map<String, Object> orderMap);
	
	public <T> List<T> getObjectsUsingJQL(Class<T> objectType, Map<String, Object> eqMap,Map<String, Object[]> betweenMap, Map<String, Object> orMap, Map<String, Object> orderMap);
	
	public <T> List<T> getObjectsUsingManual(String hqlString, Object[] values, int first, int pageSize);
	
	public <T> List<T> getObjectsUsingManual(Class<T> objectType, String[] fields, Object[] values, Map<String, Object> orderMap, int first, int pageSize);
	
	public <T> List<T> getObjectsUsingLike(Class<T> objectType, String field, String value);
}