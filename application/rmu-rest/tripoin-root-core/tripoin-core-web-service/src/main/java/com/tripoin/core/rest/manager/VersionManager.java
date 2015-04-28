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
import com.tripoin.core.dto.VersionDTO;
import com.tripoin.core.dto.Versions;
import com.tripoin.core.pojo.User;
import com.tripoin.core.pojo.Version;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("versionManager")
public class VersionManager {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	private String currentUserName;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<Versions> getVersion(Message<?> inMessage){
	
		Versions versions = new Versions();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		
		try{		
			List<User> userList = iGenericManagerJpa.getObjectsUsingParameter(User.class, new String[]{"username"}, new Object[]{currentUserName}, null, null);
			List<Version> versionList = iGenericManagerJpa.loadObjects(Version.class);
			List<VersionDTO> versionDTOList = new ArrayList<VersionDTO>();
			UserDTO userDTO = new UserDTO(userList.get(0).getUsername(), userList.get(0).getRole().getCode());
			for(Version v : versionList){
				VersionDTO versionDTO = new VersionDTO(v.getTable(),  new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format(v.getTimestamp()));
				versionDTOList.add(versionDTO);
			}
			versions.setSecurity_user(userDTO);
			versions.setMaster_version(versionDTOList);
			versions.setResponse_code("0");
			versions.setResponse_msg("Load Version Success");
			versions.setResult("SUCCESS");			
		}catch (Exception e){
			versions.setResponse_code("1");
			versions.setResponse_msg("System Error"+e.getMessage());
			versions.setResult("FAILURE");
		}
		
		setReturnStatusAndMessage(versions, responseHeaderMap);
		Message<Versions> message = new GenericMessage<Versions>(versions, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(Versions versions, Map<String, Object> responseHeaderMap){		
		responseHeaderMap.put("Return-Status", versions.getResponse_code());
		responseHeaderMap.put("Return-Status-Msg", versions.getResponse_msg());
	}
}
