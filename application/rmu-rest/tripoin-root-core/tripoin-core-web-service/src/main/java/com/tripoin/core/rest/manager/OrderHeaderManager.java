package com.tripoin.core.rest.manager;

import java.text.SimpleDateFormat;
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

import com.tripoin.core.dto.OrderHeaderDTO;
import com.tripoin.core.dto.OrderHeaders;
import com.tripoin.core.pojo.OrderHeader;
import com.tripoin.core.service.IGenericManagerJpa;

@Service("orderHeaderManager")
public class OrderHeaderManager {
	private static transient final Logger LOGGER = LoggerFactory.getLogger(OrderHeaderManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	private String order = "orderDatetime";
	
	private SimpleDateFormat formatDateJson = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
	
	private int row = 10;
	
	private static final int maxRow = 10;
	
	private String currentUserName;
	
	private Map<String, List<String>> payloads = new HashMap<String, List<String>>();
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<OrderHeaders> getOrderHeaders(Message<?> inMessage){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		return getListOrderHeaders(inMessage, this.currentUserName);		
	}
	
	@SuppressWarnings("unchecked")
	@Secured("ROLE_REST_HTTP_USER")
	public Message<OrderHeaders> setOrderHeaders(Message<?> inMessage){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    this.currentUserName = authentication.getName();
		}
		
		Map<String, List<String>> payloadMap = (Map<String, List<String>>)inMessage.getPayload();
		if(payloadMap != null){
			String orderNo = "";
			int status = 1;
			if(payloadMap.containsKey("orderNo"))
				orderNo = payloadMap.get("orderNo").get(0).toString();
			if(payloadMap.containsKey("status"))
				status = Integer.parseInt(payloadMap.get("status").get(0).toString());
			
			try {
				List<OrderHeader> orderHeaderList = iGenericManagerJpa.getObjectsUsingParameter(OrderHeader.class, new String[]{"user.username", "orderNo"}, new Object[]{currentUserName, orderNo}, order, "ASC");
				OrderHeader orderHeader = orderHeaderList.get(0);
				orderHeader.setStatus(status);
				iGenericManagerJpa.updateObject(orderHeader);
			} catch (Exception e) {
				LOGGER.error("Error Update Header : ".concat(e.getLocalizedMessage()), e);
			}
		}
		
		return getListOrderHeaders(inMessage, this.currentUserName);	
	}

	@SuppressWarnings("unchecked")
	public Message<OrderHeaders> getListOrderHeaders(Message<?> inMessage, String currentUserName){
		OrderHeaders orderHeaders = new OrderHeaders();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		payloads.clear();
		payloads.putAll((Map<String, List<String>>)inMessage.getPayload());
				
		try{
			if(payloads != null){
				if(payloads.containsKey("order"))
					order = payloads.get("order").get(0).toString();
				if(payloads.containsKey("page"))
					row = Integer.parseInt(payloads.get("page").get(0).toString());
			}			
			
			List<OrderHeader> orderHeaderList = iGenericManagerJpa.getObjectsUsingParameterManualPage(OrderHeader.class, new String[]{"user.username"}, new Object[]{currentUserName}, order, "ASC", (row-maxRow), row );
			boolean isFound;
			if (orderHeaderList!=null){
				List<OrderHeaderDTO> orderHeaderDTOList = new ArrayList<OrderHeaderDTO>();
				for (OrderHeader c : orderHeaderList) {
					LOGGER.debug("data :"+c.toString());
					OrderHeaderDTO data = new OrderHeaderDTO(c.getOrderNo(),  formatDateJson.format(c.getOrderDatetime()), c.getTotalPaid(), c.getStatus(), c.getSeat().getNo(), c.getCarriage().getNo(), c.getTrain().getNo());
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
