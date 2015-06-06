package com.tripoin.core.rest.util;

import java.util.Date;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IVersionHelper {
	
	public void updateVerision(String tableName, Date currentDate) throws Exception;
	
}
