package com.tripoin.core.service.scheduler.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class PurgeTask {
	private static transient final Logger LOGGER = LoggerFactory.getLogger(PurgeTask.class);	
	
	public void doPurgeAllData(){
		LOGGER.info("Run Scheduler");
	}
	
}
