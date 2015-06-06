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

import com.tripoin.core.dto.GeneralConnectionDTO;
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

	@Secured({"ROLE_WAITRESS", "ROLE_TRAIN"})
	public Message<GeneralConnectionDTO> getLogout(Message<?> inMessage) {

		GeneralConnectionDTO connect = new GeneralConnectionDTO();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			currentUserName = authentication.getName();
		}

		try {
			List<User> userList = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[] { "username" }, new Object[] { currentUserName }, null, null);
			User user = userList.get(0);
			user.setStatus(0);
			iGenericManagerJpa.updateObject(user);
			connect.setResponse_code("0");
			connect.setResponse_msg("Logout Success");
			connect.setResult("SUCCESS");
		} catch (Exception e) {
			connect.setResponse_code("1");
			connect.setResponse_msg("System Error : " + e.getMessage());
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
