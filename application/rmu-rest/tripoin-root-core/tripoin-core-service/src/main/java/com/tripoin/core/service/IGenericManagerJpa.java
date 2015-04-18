package com.tripoin.core.service;

import java.util.List;

public interface IGenericManagerJpa {
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception;	
	
	public void saveObject(Object objectType) throws Exception;
	
	public <T> List<T> getObjectsUsingParameter(Class clazz, String[] fields, Object[] values);
}
