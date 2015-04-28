package com.tripoin.core.rest.manager;

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

import com.tripoin.core.dto.UserDTO;
import com.tripoin.core.dto.Users;
import com.tripoin.core.pojo.User;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("logoutManager")
public class LogoutManager {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	private String currentUserName;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<Users> getLogout(Message<?> inMessage){
	
		Users users = new Users();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		try{
			List<User> userList = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[]{"username"}, new Object[]{currentUserName}, null, null);
			User user = userList.get(0);
			user.setStatus(0);
			UserDTO userDTO = new UserDTO(userList.get(0).getUsername(), userList.get(0).getRole().getCode());
			users.setSecurity_user(userDTO);
			iGenericManagerJpa.updateObject(user);		
			
			setReturnStatusAndMessage("0", "Logout Success", "SUCCESS", users, responseHeaderMap);
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error : "+e.getMessage(), "FAILURE", users, responseHeaderMap);
		}
		Message<Users> message = new GenericMessage<Users>(users, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(String responseCode, 
						String responseMsg,
						String result,
						Users users, 
						Map<String, Object> responseHeaderMap){
		
		users.setResponse_code(responseCode);
		users.setResponse_msg(responseMsg);
		users.setResult(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
}
