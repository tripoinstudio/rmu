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
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.VersionDTO;
import com.tripoin.core.dto.Versions;
import com.tripoin.core.pojo.Version;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("versionManager")
public class VersionManager {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	private SimpleDateFormat formatDateJson = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<Versions> getVersion(Message<?> inMessage){
	
		Versions versions = new Versions();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{		
			List<Version> versionList = iGenericManagerJpa.loadObjects(Version.class);
			List<VersionDTO> versionDTOList = new ArrayList<VersionDTO>();
			for(Version v : versionList){
				VersionDTO versionDTO = new VersionDTO(v.getTable(), formatDateJson.format(v.getTimestamp()), v.getRemarks());
				versionDTOList.add(versionDTO);
			}
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
