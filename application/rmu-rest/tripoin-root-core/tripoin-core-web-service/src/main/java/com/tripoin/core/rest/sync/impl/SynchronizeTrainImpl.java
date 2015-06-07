package com.tripoin.core.rest.sync.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static transient final Logger LOGGER = LoggerFactory.getLogger(SynchronizeTrainImpl.class);
	
	@Autowired
	private IRestClient restClientTrain;

	public String getNameVersion(){
		return RestConstant.VERSION_MASTER_TRAIN;
	}

	public String doSync(){
		String status = "Data Has Been Synchronize";
		if(getDiffVersion() != 0){
			Trains trains = restClientTrain.execute(Trains.class);
			for(TrainDTO trainDTO : trains.getMaster_train()){
				Train train = new Train();
				train.setCode(trainDTO.getTrain_code());
				train.setNo(trainDTO.getTrain_no());
				train.setRemarks(trainDTO.getTrain_remarks());
				List<Train> trainListCompare = iGenericManagerJpa.getObjectsUsingParameter(Train.class, new String[]{"code"}, new Object[]{trainDTO.getTrain_code()}, null, null);
				if(trainListCompare.size() == 0){
					try {
						iGenericManagerJpa.saveObject(train);
					} catch (Exception e) {
						LOGGER.error("Error Save Train Synchronize : ".concat(e.getLocalizedMessage()));
					}
				}
			}
			try {
				version.setTimestamp(currentVersion);
				iGenericManagerJpa.updateObject(version);
			} catch (Exception e) {
				LOGGER.error("Error Update Version Train : ".concat(e.getLocalizedMessage()));
			}
			status = "New Data";
		}
		return status;
	}
	
}
