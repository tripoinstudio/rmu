package com.tripoin.core.rest.sync.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.CarriageDTO;
import com.tripoin.core.dto.Carriages;
import com.tripoin.core.dto.TrainDTO;
import com.tripoin.core.dto.Trains;
import com.tripoin.core.pojo.Carriage;
import com.tripoin.core.pojo.Train;
import com.tripoin.core.rest.client.IRestClient;
import com.tripoin.core.rest.sync.base.ASynchronize;
import com.tripoin.core.rest.util.RestConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("synchronizeCarriage")
public class SynchronizeCarriageImpl extends ASynchronize {

	private static transient final Logger LOGGER = LoggerFactory.getLogger(SynchronizeCarriageImpl.class);
	
	@Autowired
	private IRestClient restClientCarriage;

	public String getNameVersion(){
		return RestConstant.VERSION_MASTER_CARRIAGE;
	}

	public String doSync(){
		String status = "Data Has Been Synchronize";
		if(getDiffVersion() != 0){
			Carriages carriages = restClientCarriage.execute(Carriages.class);
			for(CarriageDTO carriageDTO : carriages.getMaster_carriage()){
				Carriage carriage = new Carriage();
				carriage.setCode(carriageDTO.getCarriage_code());
				carriage.setNo(carriageDTO.getCarriage_no());
				carriage.setRemarks(carriageDTO.getCarriage_remarks());
				List<Carriage> carriageListCompare = iGenericManagerJpa.getObjectsUsingParameter(Carriage.class, new String[]{"code"}, new Object[]{carriageDTO.getCarriage_code()}, null, null);
				if(carriageListCompare.size() == 0){
					try {
						iGenericManagerJpa.saveObject(carriage);
					} catch (Exception e) {
						LOGGER.error("Error Save Carriage Synchronize : ".concat(e.getLocalizedMessage()));
					}
				}
			}
			try {
				version.setTimestamp(currentVersion);
				iGenericManagerJpa.updateObject(version);
			} catch (Exception e) {
				LOGGER.error("Error Update Version Carriage : ".concat(e.getLocalizedMessage()));
			}
			status = "New Data";
		}
		return status;
	}
	
}
