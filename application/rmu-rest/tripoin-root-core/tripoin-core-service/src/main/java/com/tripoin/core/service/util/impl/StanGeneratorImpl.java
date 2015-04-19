package com.tripoin.core.service.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dao.util.IStanDao;
import com.tripoin.core.pojo.Stan;
import com.tripoin.core.service.util.IStanGenerator;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service
public class StanGeneratorImpl implements IStanGenerator {

	@Autowired
	IStanDao stanDao;
	
	public void updateStan(Stan stan) {
		Long stanCounter = stan.getStanCounter();
		if(stanCounter >= stan.getStanMax()-stanCounter)
			stanCounter = Long.parseLong("0");
		stanCounter++;
		stanDao.updateStan(stanCounter, stan.getId());
	}

	@Override
	public Stan getSystemTraceAuditNumber(Long userId) {
		Stan stan = stanDao.loadStan(userId);
		updateStan(stan);
		return stan;
	}

}
