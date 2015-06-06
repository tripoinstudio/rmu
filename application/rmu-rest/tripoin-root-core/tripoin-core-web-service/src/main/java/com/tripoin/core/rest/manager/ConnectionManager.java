package com.tripoin.core.rest.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.integration.Message;
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.GeneralConnectionDTO;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("connectionManager")
public class ConnectionManager {
	
	@Secured({"ROLE_WAITRESS", "ROLE_TRAIN", "ROLE_DEFAULT"})
	public Message<GeneralConnectionDTO> getConnection(Message<?> inMessage){	
		GeneralConnectionDTO connect = new GeneralConnectionDTO();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{			
			connect.setResponse_code("0");
			connect.setResponse_msg("Connection Success");
			connect.setResult("SUCCESS");			
		}catch (Exception e){
			connect.setResponse_code("1");
			connect.setResponse_msg("System Error"+e.getMessage());
			connect.setResult("FAILURE");
		}
		
		setReturnStatusAndMessage(connect, responseHeaderMap);
		Message<GeneralConnectionDTO> message = new GenericMessage<GeneralConnectionDTO>(connect, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(GeneralConnectionDTO connect, Map<String, Object> responseHeaderMap){		
		responseHeaderMap.put("Return-Status", connect.getResponse_code());
		responseHeaderMap.put("Return-Status-Msg", connect.getResponse_msg());
	}
}
