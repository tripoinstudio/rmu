package com.tripoin.core.service.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tripoin.core.service.scheduler.task.PurgeTask;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public class PurgeScheduler extends QuartzJobBean {
		
	private PurgeTask purgeTask;

	public void setPurgeTask(PurgeTask purgeTask) {
		this.purgeTask = purgeTask;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		purgeTask.doPurgeAllData();
	}

}
