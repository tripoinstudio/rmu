package com.tripoin.core.dao.util;

import com.tripoin.core.pojo.SystemParameter;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface ISystemParameterDao {
	
	public SystemParameter loadValue(String name);

}
