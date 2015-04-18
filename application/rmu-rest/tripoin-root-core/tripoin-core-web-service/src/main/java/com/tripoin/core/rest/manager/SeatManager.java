package com.tripoin.core.rest.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHeaders;
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tripoin.core.domain.SeatDTO;
import com.tripoin.core.domain.Seats;
import com.tripoin.core.pojo.Seat;
import com.tripoin.core.service.IGenericManagerJpa;

@Service("seatManager")
public class SeatManager {
	private static transient final Logger LOGGER = LoggerFactory.getLogger(CarriageManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<Seats> getSeats(Message<?> inMessage){
	
		Seats seats = new Seats();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			MessageHeaders headers = inMessage.getHeaders();
			List<Seat> seatList = iGenericManagerJpa.loadObjects(Seat.class);
			boolean isFound;
			if (seatList!=null){
				List<SeatDTO> seatDTOList = new ArrayList<SeatDTO>();
				for (Seat c : seatList) {
					LOGGER.debug("data :"+c.toString());
					SeatDTO data = new SeatDTO(c.getId(), c.getNo(), c.getRemarks());
					seatDTOList.add(data);
				} 
				seats.setMaster_seat(seatDTOList);
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", seats, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Seat Not Found", "EMPTY", seats, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error"+e.getMessage(), "FAILURE", seats, responseHeaderMap);
		}
		Message<Seats> message = new GenericMessage<Seats>(seats, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(String responseCode, String responseMsg, String result, Seats seats, Map<String, Object> responseHeaderMap){
		
		seats.setResponse_code(responseCode);
		seats.setResponse_msg(responseMsg);
		seats.setResult(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
}
