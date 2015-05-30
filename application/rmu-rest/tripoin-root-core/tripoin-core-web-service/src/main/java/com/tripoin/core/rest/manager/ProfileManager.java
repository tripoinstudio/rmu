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

import com.tripoin.core.dto.Profiles;
import com.tripoin.core.pojo.Profile;
import com.tripoin.core.pojo.Ticket;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("profileManager")
public class ProfileManager {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	private String currentUserName;
	
	@Secured({"ROLE_WAITRESS", "ROLE_PASSENGER"})
	public Message<Profiles> getProfile(Message<?> inMessage){	
		Profiles profiles = new Profiles();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		try{			
			List<Profile> profileList = iGenericManagerJpa.getObjectsUsingParameter(Profile.class, new String[]{"user.username"}, new Object[]{currentUserName}, null, null);
			Profile profile = profileList.get(0);
			if("ROLE_PASSENGER".equals(profile.getUser().getRole().getCode())){
				List<Ticket> ticketList = iGenericManagerJpa.getObjectsUsingParameter(Ticket.class, new String[]{"profile"}, new Object[]{profile}, null, null);
				
			}
			profiles.setResponse_code("0");
			profiles.setResponse_msg("Connection Success");
			profiles.setResult("SUCCESS");			
		}catch (Exception e){
			profiles.setResponse_code("1");
			profiles.setResponse_msg("System Error"+e.getMessage());
			profiles.setResult("FAILURE");
		}
		
		setReturnStatusAndMessage(profiles, responseHeaderMap);
		Message<Profiles> message = new GenericMessage<Profiles>(profiles, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(Profiles profiles, Map<String, Object> responseHeaderMap){		
		responseHeaderMap.put("Return-Status", profiles.getResponse_code());
		responseHeaderMap.put("Return-Status-Msg", profiles.getResponse_msg());
	}
}
