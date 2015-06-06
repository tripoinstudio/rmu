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

import com.tripoin.core.dto.UserDTO;
import com.tripoin.core.dto.Users;
import com.tripoin.core.dto.VersionDTO;
import com.tripoin.core.pojo.User;
import com.tripoin.core.pojo.Version;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("loggedInManager")
public class LoggedInManager {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	private String currentUserName;
	
	@Secured({"ROLE_WAITRESS", "ROLE_TRAIN"})
	public Message<Users> getLoggedIn(Message<?> inMessage){
	
		Users users = new Users();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		try{
			List<User> userList = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[]{"username"}, new Object[]{currentUserName}, null, null);
			User user = userList.get(0);
			user.setStatus(1);
			UserDTO userDTO = new UserDTO(userList.get(0).getUsername(), userList.get(0).getRole().getCode());
			List<Version> versionList = iGenericManagerJpa.loadObjects(Version.class);
			List<VersionDTO> versionDTOList = new ArrayList<VersionDTO>();
			for(Version v : versionList){
				VersionDTO versionDTO = new VersionDTO(v.getTable(),  new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(v.getTimestamp()), v.getRemarks());
				versionDTOList.add(versionDTO);
			}
			users.setMaster_version(versionDTOList);
			users.setSecurity_user(userDTO);
			iGenericManagerJpa.updateObject(user);		
			
			setReturnStatusAndMessage("0", "Login Success", "SUCCESS", users, responseHeaderMap);
			
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
