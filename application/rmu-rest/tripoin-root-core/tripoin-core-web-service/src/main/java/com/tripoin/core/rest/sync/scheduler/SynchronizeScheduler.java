package com.tripoin.core.rest.sync.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tripoin.core.rest.sync.ISynchronize;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class SynchronizeScheduler extends QuartzJobBean {
		
	private ISynchronize synchronizeTrain;
	
	public void setSynchronizeTrain(ISynchronize synchronizeTrain) {
		this.synchronizeTrain = synchronizeTrain;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		synchronizeTrain.doSync();
	}

}
