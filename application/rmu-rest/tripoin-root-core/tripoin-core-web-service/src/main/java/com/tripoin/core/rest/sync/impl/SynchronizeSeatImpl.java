package com.tripoin.core.rest.sync.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.CarriageDTO;
import com.tripoin.core.dto.Carriages;
import com.tripoin.core.dto.SeatDTO;
import com.tripoin.core.dto.Seats;
import com.tripoin.core.dto.TrainDTO;
import com.tripoin.core.dto.Trains;
import com.tripoin.core.pojo.Carriage;
import com.tripoin.core.pojo.Seat;
import com.tripoin.core.pojo.Train;
import com.tripoin.core.rest.client.IRestClient;
import com.tripoin.core.rest.sync.base.ASynchronize;
import com.tripoin.core.rest.util.RestConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("synchronizeSeat")
public class SynchronizeSeatImpl extends ASynchronize {

	private static transient final Logger LOGGER = LoggerFactory.getLogger(SynchronizeSeatImpl.class);
	
	@Autowired
	private IRestClient restClientSeat;

	public String getNameVersion(){
		return RestConstant.VERSION_MASTER_SEAT;
	}

	public String doSync(){
		String status = "Data Has Been Synchronize";
		if(getDiffVersion() != 0){
			Seats seats = restClientSeat.execute(Seats.class);
			for(SeatDTO seatDTO : seats.getMaster_seat()){
				Seat seat = new Seat();
				seat.setCode(seatDTO.getSeat_code());
				seat.setNo(seatDTO.getSeat_no());
				seat.setRemarks(seatDTO.getSeat_remarks());
				List<Seat> carriageListCompare = iGenericManagerJpa.getObjectsUsingParameter(Seat.class, new String[]{"code"}, new Object[]{seatDTO.getSeat_code()}, null, null);
				if(carriageListCompare.size() == 0){
					try {
						iGenericManagerJpa.saveObject(seat);
					} catch (Exception e) {
						LOGGER.error("Error Save Seat Synchronize : ".concat(e.getLocalizedMessage()));
					}
				}
			}
			try {
				version.setTimestamp(currentVersion);
				iGenericManagerJpa.updateObject(version);
			} catch (Exception e) {
				LOGGER.error("Error Update Data Seat : ".concat(e.getLocalizedMessage()));
			}
			status = "New Data";
		}
		return status;
	}
	
}
