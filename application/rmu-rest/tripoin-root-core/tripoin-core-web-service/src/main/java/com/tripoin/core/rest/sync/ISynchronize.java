package com.tripoin.core.rest.sync;

import java.util.Date;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface ISynchronize {
	
	public String doSync();
	
	public Date getVersion();
	
	public Integer getDiffVersion();
	
	public String getNameVersion();
}
