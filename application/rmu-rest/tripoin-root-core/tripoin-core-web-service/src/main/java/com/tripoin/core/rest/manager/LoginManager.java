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
import com.tripoin.core.util.ELoggedIn;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("loginManager")
public class LoginManager {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(LoginManager.class);
	private String STATUSLOGIN = ELoggedIn.SUCCESS.toString();

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<OrderHeaderWithUsers> getLogin(Message<?> inMessage){
	
		OrderHeaderWithUsers orderHeaderWithUsers = new OrderHeaderWithUsers();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			List<OrderHeader> orderHeaderList = iGenericManagerJpa.loadObjects(OrderHeader.class);
			
			if (orderHeaderList!=null){
				List<OrderHeaderDTO> orderHeaderDTOList = new ArrayList<OrderHeaderDTO>();
				UserDTO user = new UserDTO();
				boolean role = true;
				for (OrderHeader o : orderHeaderList) {
					if(role){
						user = new UserDTO(o.getUser().getUsername(), o.getUser().getRole().getCode());
						if(o.getUser().getStatus() == 1){
							STATUSLOGIN = ELoggedIn.LOGGEDIN.toString();
							break;
						}
						role = false;
					}
					
					LOGGER.debug("data :"+o.toString());
					OrderHeaderDTO data = new OrderHeaderDTO(o.getOrderNo(), o.getOrderDatetime(), o.getTotalPaid(), o.getStatus(), o.getUser().getUsername(), o.getSeat().getNo(), o.getCarriage().getNo(), o.getTrain().getNo());
					orderHeaderDTOList.add(data);					
				} 
				if(ELoggedIn.SUCCESS.toString().equals(STATUSLOGIN)){
					orderHeaderWithUsers.setTrx_order_header(orderHeaderDTOList);
					orderHeaderWithUsers.setSecurity_user(user);
				}
			}else{	
				STATUSLOGIN = ELoggedIn.EMPTY.toString();
			}			
			if (ELoggedIn.SUCCESS.toString().equals(STATUSLOGIN)){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", orderHeaderWithUsers, responseHeaderMap);
			}else if (ELoggedIn.LOGGEDIN.toString().equals(STATUSLOGIN)){
				setReturnStatusAndMessage("1", "User Has Been Login", "LOGGEDIN", orderHeaderWithUsers, responseHeaderMap);
			}else if (ELoggedIn.EMPTY.toString().equals(STATUSLOGIN)){
				setReturnStatusAndMessage("2", "Order Not Found", "EMPTY", orderHeaderWithUsers, responseHeaderMap);								
			}else {
				setReturnStatusAndMessage("3", "User Blocked", "BLOCKED", orderHeaderWithUsers, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("4", "System Error : "+e.getMessage(), "FAILURE", orderHeaderWithUsers, responseHeaderMap);
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
