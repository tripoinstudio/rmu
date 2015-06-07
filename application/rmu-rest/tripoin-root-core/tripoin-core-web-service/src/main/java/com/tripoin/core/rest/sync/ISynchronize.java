package com.tripoin.core.rest.sync;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface ISynchronize {
	
	public String doSync();
	
	public Integer getDiffVersion();
	
	public String getNameVersion();
	
}
