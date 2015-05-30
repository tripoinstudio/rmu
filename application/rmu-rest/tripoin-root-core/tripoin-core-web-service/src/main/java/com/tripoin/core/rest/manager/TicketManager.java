package com.tripoin.core.rest.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.integration.Message;
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.Tickets;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("ticketManager")
public class TicketManager {
	
	@Secured({"ROLE_DEFAULT"})
	public Message<Tickets> getTicket(Message<?> inMessage){	
		Tickets tickets = new Tickets();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{			
			tickets.setResponse_code("0");
			tickets.setResponse_msg("Connection Success");
			tickets.setResult("SUCCESS");			
		}catch (Exception e){
			tickets.setResponse_code("1");
			tickets.setResponse_msg("System Error"+e.getMessage());
			tickets.setResult("FAILURE");
		}
		
		setReturnStatusAndMessage(tickets, responseHeaderMap);
		Message<Tickets> message = new GenericMessage<Tickets>(tickets, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(Tickets tickets, Map<String, Object> responseHeaderMap){		
		responseHeaderMap.put("Return-Status", tickets.getResponse_code());
		responseHeaderMap.put("Return-Status-Msg", tickets.getResponse_msg());
	}
}
