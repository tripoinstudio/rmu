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
	private ISynchronize synchronizeCarriage;
	private ISynchronize synchronizeSeat;
	private ISynchronize synchronizeMenu;
	
	public void setSynchronizeTrain(ISynchronize synchronizeTrain) {
		this.synchronizeTrain = synchronizeTrain;
	}
	
	public void setSynchronizeCarriage(ISynchronize synchronizeCarriage) {
		this.synchronizeCarriage = synchronizeCarriage;
	}

	public void setSynchronizeSeat(ISynchronize synchronizeSeat) {
		this.synchronizeSeat = synchronizeSeat;
	}

	public void setSynchronizeMenu(ISynchronize synchronizeMenu) {
		this.synchronizeMenu = synchronizeMenu;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		synchronizeTrain.doSync();
		synchronizeCarriage.doSync();
		synchronizeSeat.doSync();
		synchronizeMenu.doSync();
	}

}
