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

import com.tripoin.core.domain.OrderHeaderDTO;
import com.tripoin.core.domain.OrderHeaderWithUsers;
import com.tripoin.core.domain.UserDTO;
import com.tripoin.core.pojo.OrderHeader;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("loginManager")
public class LoginManager {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(LoginManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<OrderHeaderWithUsers> getLogin(Message<?> inMessage){
	
		OrderHeaderWithUsers orderHeaderWithUsers = new OrderHeaderWithUsers();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			List<OrderHeader> orderHeaderList = iGenericManagerJpa.loadObjects(OrderHeader.class);
			boolean isFound;
			if (orderHeaderList!=null){
				List<OrderHeaderDTO> orderHeaderDTOList = new ArrayList<OrderHeaderDTO>();
				UserDTO user = new UserDTO();
				boolean role = true;
				for (OrderHeader o : orderHeaderList) {
					LOGGER.debug("data :"+o.toString());
					OrderHeaderDTO data = new OrderHeaderDTO(o.getOrderNo(), o.getOrderDatetime(), o.getTotalPaid(), o.getStatus(), o.getUser().getUsername(), o.getSeat().getNo(), o.getCarriage().getNo(), o.getTrain().getNo());
					orderHeaderDTOList.add(data);
					
					if(role)
						user = new UserDTO(o.getUser().getUsername(), o.getUser().getRole().getCode());
					
					role = false;
				} 
				orderHeaderWithUsers.setTrx_order_header(orderHeaderDTOList);
				orderHeaderWithUsers.setSecurity_user(user);
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", orderHeaderWithUsers, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Carriage Not Found", "EMPTY", orderHeaderWithUsers, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error"+e.getMessage(), "FAILURE", orderHeaderWithUsers, responseHeaderMap);
		}
		Message<OrderHeaderWithUsers> message = new GenericMessage<OrderHeaderWithUsers>(orderHeaderWithUsers, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(String responseCode, 
						String responseMsg,
						String result,
						OrderHeaderWithUsers orderHeaderWithUsers, 
						Map<String, Object> responseHeaderMap){
		
		orderHeaderWithUsers.setResponse_code(responseCode);
		orderHeaderWithUsers.setResponse_msg(responseMsg);
		orderHeaderWithUsers.setResult(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
}
