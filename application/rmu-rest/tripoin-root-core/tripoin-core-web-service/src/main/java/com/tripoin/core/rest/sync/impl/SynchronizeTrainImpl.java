package com.tripoin.core.rest.sync.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.TrainDTO;
import com.tripoin.core.dto.Trains;
import com.tripoin.core.pojo.Train;
import com.tripoin.core.rest.client.IRestClient;
import com.tripoin.core.rest.sync.base.ASynchronize;
import com.tripoin.core.rest.util.RestConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("synchronizeTrain")
public class SynchronizeTrainImpl extends ASynchronize {
	
	@Autowired
	private IRestClient restClientTrain;

	public String getNameVersion(){
		return RestConstant.VERSION_MASTER_TRAIN;
	}

	public String doSync(){
		if(getDiffVersion() != 0){
			Trains trains = restClientTrain.execute(Trains.class);
			for(TrainDTO trainDTO : trains.getMaster_train()){
				Train train = new Train();
				train.setCode(trainDTO.getTrain_code());
				train.setNo(trainDTO.getTrain_no());
				train.setRemarks(trainDTO.getTrain_remarks());
				Train trainCompare = iGenericManagerJpa.getObjectsUsingParameter(Train.class, new String[]{"code"}, new Object[]{trainDTO.getTrain_code()}, null, null).get(0);
				if(trainCompare == null){
					try {
						iGenericManagerJpa.saveObject(train);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	
}
