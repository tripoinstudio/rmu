package com.tripoin.core.service;

import java.io.Serializable;
import java.util.List;

public interface IGenericManagerJpa {
	public <T> T loadObject(Class<T> objectType, Serializable key) throws Exception ;
	
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception;	
	
	public void saveObject(Object objectType) throws Exception;
	
	public void updateObject(Object objectType) throws Exception;
	
	public <T> List<T> getObjectsUsingParameter(Class<T> objectType, String[] fields, Object[] values, String orderBy, String order);
	
	public <T> List<T> getObjectsUsingParameter(Class<T> objectType, String[] eqList, String[] orList, String[] betweenList, Object[] values, String orderBy, String order);

	public <T> List<T> getObjectsUsingParameterManualPage(Class<T> objectType, String[] fields, Object[] values, String orderBy, String order, int first, int pageSize);

	public <T> List<T> getObjectsUsingParameterManualPage(String hqlString, Object[] values, int first, int pageSize);
	
	public <T> List<T> getObjectsUsingParameterLike(Class<T> objectType, String field, String value);
}
