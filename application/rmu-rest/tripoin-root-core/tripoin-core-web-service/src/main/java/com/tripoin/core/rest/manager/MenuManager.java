package com.tripoin.core.rest.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.message.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.MenuDTO;
import com.tripoin.core.dto.Menus;
import com.tripoin.core.pojo.Image;
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.service.IGenericManagerJpa;

@Service("menuManager")
public class MenuManager {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Secured("ROLE_REST_HTTP_USER")
	public Message<Menus> getMenus(Message<?> inMessage){
	
		Menus menus = new Menus();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		
		try{
			List<Menu> menuList = iGenericManagerJpa.loadObjects(Menu.class);			
			boolean isFound;
			if (menuList!=null){
				List<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();
				for (Menu c : menuList) {
					List<Image> imageList = iGenericManagerJpa.getObjectsUsingParameter(Image.class, new String[]{"menu", "status"}, new Object[]{c, 1}, null, null);
					String menuImage = "";
					if(imageList.get(0).getStatus() == 1)
						menuImage = imageList.get(0).getName();
					MenuDTO data = new MenuDTO(c.getCode(), c.getName(), c.getType(), c.getPrice(), menuImage, c.getRating());
					menuDTOList.add(data);
				} 
				menus.setMaster_menu(menuDTOList);
				isFound = true;
			}else{				
				isFound = false;
			}			
			if (isFound){
				setReturnStatusAndMessage("0", "Load Data Success", "SUCCESS", menus, responseHeaderMap);
			}else{
				setReturnStatusAndMessage("2", "Menu Not Found", "EMPTY", menus, responseHeaderMap);								
			}
			
		}catch (Exception e){
			setReturnStatusAndMessage("1", "System Error"+e.getMessage(), "FAILURE", menus, responseHeaderMap);
		}
		Message<Menus> message = new GenericMessage<Menus>(menus, responseHeaderMap);
		return message;		
	}
	
	private void setReturnStatusAndMessage(String responseCode, String responseMsg, String result, Menus menus, Map<String, Object> responseHeaderMap){
		
		menus.setResponse_code(responseCode);
		menus.setResponse_msg(responseMsg);
		menus.setResult(result);
		responseHeaderMap.put("Return-Status", responseCode);
		responseHeaderMap.put("Return-Status-Msg", responseMsg);
	}
}
