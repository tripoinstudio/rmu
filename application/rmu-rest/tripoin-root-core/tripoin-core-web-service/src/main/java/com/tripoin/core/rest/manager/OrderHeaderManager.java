package com.tripoin.core.rest.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.tripoin.core.pojo.User;
import com.tripoin.core.pojo.VersionFilter;
import com.tripoin.core.rest.util.IVersionHelper;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.util.RoleConstant;

@Service("orderHeaderManager")
public class OrderHeaderManager {
	private static transient final Logger LOGGER = LoggerFactory.getLogger(OrderHeaderManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private IVersionHelper iVersionHelper;
	
	private String order = "orderDatetime";
	
	private SimpleDateFormat formatDateJson = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
	
	@SuppressWarnings("unused")
	private int row = 10;
	
	@SuppressWarnings("unused")
	private static final int maxRow = 10;
	
	private String currentUserName;
	
	private String defaultUserName;
	
	private Date currentDate;
	
	private String lastVersion;
	
	private Date lastVersionTimestamp;
	
	private SimpleDateFormat formatVersionTimestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
	
	private Map<String, List<String>> payloads = new HashMap<String, List<String>>();
	
	@Secured({"ROLE_WAITRESS", "ROLE_TRAIN"})
	public Message<OrderHeaders> getOrderHeaders(Message<?> inMessage){
		currentDate = new Date();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		return getListOrderHeaders(inMessage);		
	}
	
	@SuppressWarnings("unchecked")
	@Secured({"ROLE_WAITRESS", "ROLE_TRAIN"})
	public Message<OrderHeaders> setOrderHeaders(Message<?> inMessage){
		currentDate = new Date();
		
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
				List<OrderHeader> orderHeaderList = iGenericManagerJpa.getObjectsUsingParameter(OrderHeader.class, new String[]{"user.username", "orderNo"}, new Object[]{currentUserName, orderNo}, order, "DESC");
				OrderHeader orderHeader = orderHeaderList.get(0);
				orderHeader.setStatus(status);
				orderHeader.setVersionTimestamp(currentDate);
				List<VersionFilter> versionFilterList = iGenericManagerJpa.getObjectsUsingParameter(VersionFilter.class, new String[]{"user.username"}, new Object[]{currentUserName}, null, null);
				VersionFilter versionFilter = versionFilterList.get(0);
				versionFilter.setVersionOrderHeader(currentDate);
				iGenericManagerJpa.updateObject(versionFilter);
				iGenericManagerJpa.updateObject(orderHeader);
				iVersionHelper.updateVerision("trx_order_header", currentDate);
				iVersionHelper.updateVerision("master_menu", currentDate);
			} catch (Exception e) {
				LOGGER.error("Error Update Header : ".concat(e.getLocalizedMessage()), e);
			}
		}
		
		return getListOrderHeaders(inMessage);	
	}

	@SuppressWarnings("unchecked")
	public Message<OrderHeaders> getListOrderHeaders(Message<?> inMessage){
		OrderHeaders orderHeaders = new OrderHeaders();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		payloads.clear();
		payloads.putAll((Map<String, List<String>>)inMessage.getPayload());
				
		try{
			if(payloads != null){
				if(payloads.containsKey("lastVersion")){
					lastVersion = payloads.get("lastVersion").get(0).toString();
					try {
						lastVersionTimestamp = formatVersionTimestamp.parse(lastVersion);
					} catch (ParseException e) {
						lastVersionTimestamp = currentDate;
						throw new Exception();
					}
				}else{
					lastVersionTimestamp = currentDate;
				}
				if(payloads.containsKey("order"))
					order = payloads.get("order").get(0).toString();
				if(payloads.containsKey("page"))
					row = Integer.parseInt(payloads.get("page").get(0).toString());
			}			
			defaultUserName = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[]{"role.code"}, new Object[]{RoleConstant.ROLE_DEFAULT}, null, null).get(0).getUsername();
			List<OrderHeader> orderHeaderList = iGenericManagerJpa.getObjectsUsingParameterManualJQL(" FROM OrderHeader o WHERE ( o.user.username = ? OR o.user.username = ? ) AND o.versionTimestamp > ? ORDER BY o.orderNo DESC", new Object[]{currentUserName, defaultUserName, lastVersionTimestamp});
			boolean isFound;
			if (orderHeaderList!=null){
				List<OrderHeaderDTO> orderHeaderDTOList = new ArrayList<OrderHeaderDTO>();
				for (OrderHeader c : orderHeaderList) {
					OrderHeaderDTO data = new OrderHeaderDTO(c.getOrderNo(), formatDateJson.format(c.getOrderDatetime()), c.getTotalPaid(), c.getStatus(), c.getSeat().getNo(), c.getCarriage().getNo(), c.getTrain().getNo());
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
