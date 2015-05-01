package com.tripoin.core.rest.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.TrainDTO;
import com.tripoin.core.dto.Trains;
import com.tripoin.core.pojo.Train;
import com.tripoin.core.service.IGenericManagerJpa;

@Service("trainManager")
public class TrainManager {
	private static transient final Logger LOGGER = LoggerFactory.getLogger(TrainManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<Trains> getTrains(Message<?> inMessage){
	
		Trains trains = new Trains();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			List<Train> trainList = iGenericManagerJpa.loadObjects(Train.class);
			boolean isFound;
			if (trainList!=null){
				List<TrainDTO> trainDTOList = new ArrayList<TrainDTO>();
				for (Train c : trainList) {
					LOGGER.debug("data :"+c.toString());
					TrainDTO data = new TrainDTO(c.getCode(), c.getNo(), c.getRemarks());
					trainDTOList.add(data);
				} 
				trains.setMaster_train(trainDTOList);
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", trains, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Carriage Not Found", "EMPTY", trains, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error"+e.getMessage(), "FAILURE", trains, responseHeaderMap);
		}
		Message<Trains> message = new GenericMessage<Trains>(trains, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(String responseCode, String responseMsg, String result, Trains trains, Map<String, Object> responseHeaderMap){
		
		trains.setResponse_code(responseCode);
		trains.setResponse_msg(responseMsg);
		trains.setResult(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
}
