package com.tripoin.core.rest.sync.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.tripoin.core.rest.sync.base.ASynchronize;
import com.tripoin.core.rest.util.RestConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("synchronizeTrain")
public class SynchronizeTrainImpl extends ASynchronize {

	public String getNameVersion(){
		return RestConstant.VERSION_MASTER_TRAIN;
	}
}
