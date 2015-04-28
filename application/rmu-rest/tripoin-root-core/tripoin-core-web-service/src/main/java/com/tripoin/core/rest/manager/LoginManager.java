package com.tripoin.core.rest.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.OrderHeaderDTO;
import com.tripoin.core.dto.OrderHeaderWithUsers;
import com.tripoin.core.dto.UserDTO;
import com.tripoin.core.dto.VersionDTO;
import com.tripoin.core.pojo.OrderHeader;
import com.tripoin.core.pojo.User;
import com.tripoin.core.pojo.Version;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.util.ELoggedIn;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("loginManager")
public class LoginManager {
	
	private String STATUSLOGIN = ELoggedIn.SUCCESS.toString();

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	private String currentUserName;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<OrderHeaderWithUsers> getLogin(Message<?> inMessage){
	
		OrderHeaderWithUsers orderHeaderWithUsers = new OrderHeaderWithUsers();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		try{
			List<User> userList = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[]{"username"}, new Object[]{currentUserName}, null, null);
			UserDTO userDTO = new UserDTO(userList.get(0).getUsername(), userList.get(0).getRole().getCode());
			orderHeaderWithUsers.setSecurity_user(userDTO);
			if(userList.get(0).getStatus() == 0){
				List<OrderHeader> orderHeaderList = iGenericManagerJpa.getObjectsUsingParameterManualPage(OrderHeader.class, new String[]{"user.username"}, new Object[]{currentUserName}, "orderDatetime", "ASC", 0 , 20);
				List<Version> versionList = iGenericManagerJpa.loadObjects(Version.class);
				if (orderHeaderList!=null){
					List<OrderHeaderDTO> orderHeaderDTOList = new ArrayList<OrderHeaderDTO>();
					for (OrderHeader o : orderHeaderList) {		
						OrderHeaderDTO data = new OrderHeaderDTO(o.getOrderNo(), new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(o.getOrderDatetime()), o.getTotalPaid(), o.getStatus(), o.getSeat().getNo(), o.getCarriage().getNo(), o.getTrain().getNo());
						orderHeaderDTOList.add(data);					
					}
					List<VersionDTO> versionDTOList = new ArrayList<VersionDTO>();
					for(Version v : versionList){
						VersionDTO versionDTO = new VersionDTO(v.getTable(),  new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(v.getTimestamp()));
						versionDTOList.add(versionDTO);
					}
					orderHeaderWithUsers.setTrx_order_header(orderHeaderDTOList);
					orderHeaderWithUsers.setMaster_version(versionDTOList);
				}else{	
					STATUSLOGIN = ELoggedIn.EMPTY.toString();
				}	
			}else{
				STATUSLOGIN = ELoggedIn.LOGGEDIN.toString();				
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
