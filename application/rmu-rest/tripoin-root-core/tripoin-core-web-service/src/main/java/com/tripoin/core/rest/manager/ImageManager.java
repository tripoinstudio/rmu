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
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.ImageDTO;
import com.tripoin.core.dto.Images;
import com.tripoin.core.pojo.Image;
import com.tripoin.core.service.IGenericManagerJpa;

@Service("imageManager")
public class ImageManager {
	
	private static transient final Logger LOGGER = LoggerFactory.getLogger(ImageManager.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	private String menuCode = "";
	
	@SuppressWarnings("unchecked")
	@Secured({"ROLE_WAITRESS", "ROLE_TRAIN"})
	public Message<Images> getImages(Message<?> inMessage){	
		Images images = new Images();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		Map<String, List<String>> payloadMap = (Map<String, List<String>>)inMessage.getPayload();
		if(payloadMap != null){
			if(payloadMap.containsKey("menuCode"))
				menuCode = payloadMap.get("menuCode").get(0).toString();
		}
		
		try{
			List<Image> imageList = iGenericManagerJpa.getObjectsUsingParameter(Image.class, new String[]{"code"}, new Object[]{menuCode}, null, null);
			boolean isFound;
			if (imageList!=null){
				List<ImageDTO> imageDTOList = new ArrayList<ImageDTO>();
				for (Image c : imageList) {
					LOGGER.debug("data :"+c.toString());
					ImageDTO data = new ImageDTO(c.getCode(), c.getName(), c.getStatus());
					imageDTOList.add(data);
				} 
				images.setMaster_image(imageDTOList);
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", images, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Image Not Found", "EMPTY", images, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error"+e.getMessage(), "FAILURE", images, responseHeaderMap);
		}
		Message<Images> message = new GenericMessage<Images>(images, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(String responseCode, String responseMsg, String result, Images images, Map<String, Object> responseHeaderMap){		
		images.setResponse_code(responseCode);
		images.setResponse_msg(responseMsg);
		images.setResult(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
	
}
