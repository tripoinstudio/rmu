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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tripoin.core.domain.OrderHeaderDTO;
import com.tripoin.core.domain.OrderHeaders;
import com.tripoin.core.pojo.OrderHeader;
import com.tripoin.core.service.IGenericManagerJpa;

@Service("orderHeaderManager")
public class OrderHeaderManager {
	private static transient final Logger LOGGER = LoggerFactory.getLogger(OrderHeaderManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	private String currentUserName;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<OrderHeaders> getOrderHeaders(Message<?> inMessage){
		OrderHeaders orderHeaders = new OrderHeaders();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		try{
			List<OrderHeader> orderHeaderList = iGenericManagerJpa.getObjectsUsingParameter(OrderHeader.class, new String[]{"user.username"}, new Object[]{currentUserName}, null, null);
			boolean isFound;
			if (orderHeaderList!=null){
				List<OrderHeaderDTO> orderHeaderDTOList = new ArrayList<OrderHeaderDTO>();
				for (OrderHeader c : orderHeaderList) {
					LOGGER.debug("data :"+c.toString());
					OrderHeaderDTO data = new OrderHeaderDTO(c.getOrderNo(), c.getOrderDatetime(), c.getTotalPaid(), c.getStatus(), c.getUser().getUsername(), c.getSeat().getNo(), c.getCarriage().getNo(), c.getTrain().getNo());
					orderHeaderDTOList.add(data);
				} 
				orderHeaders.setTrx_order_header(orderHeaderDTOList);
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", orderHeaders, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Order Header Not Found", "EMPTY", orderHeaders, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error"+e.getMessage(), "FAILURE", orderHeaders, responseHeaderMap);
		}
		Message<OrderHeaders> message = new GenericMessage<OrderHeaders>(orderHeaders, responseHeaderMap);
		return message;	
	}
	
	private void setReturnStatusAndMessage(String responseCode, String responseMsg, String result, OrderHeaders orderHeaders, Map<String, Object> responseHeaderMap){
		
		orderHeaders.setResponse_code(responseCode);
		orderHeaders.setResponse_msg(responseMsg);
		orderHeaders.setResult(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
}
