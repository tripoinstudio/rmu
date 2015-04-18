package com.tripoin.core.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDaoJpa {
	public <T> T loadObject(Class<T> objectType, Serializable key) throws Exception ;
	
	public <T> List<T> loadObjects(Class<T> objectType) throws Exception;
	
	public <T> List<T> loadObjectsFilterKey(Class<T> objectType, Serializable key) throws Exception;
	
	public void saveObject(Object objectType) throws Exception;
	
	public void updateObject(Class<?> objectType, Serializable key) throws Exception;
	
	public void deleteObject(Class<?> objectType) throws Exception;
	
	public <T> List<T> getObjectsUsingJQL(Class<T> objectType, String[] fields, Object[] values);

}