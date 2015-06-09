package com.tripoin.core.rest.sync.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tripoin.core.dto.CarriageDTO;
import com.tripoin.core.dto.Carriages;
import com.tripoin.core.dto.MenuDTO;
import com.tripoin.core.dto.Menus;
import com.tripoin.core.dto.SeatDTO;
import com.tripoin.core.dto.Seats;
import com.tripoin.core.dto.TrainDTO;
import com.tripoin.core.dto.Trains;
import com.tripoin.core.pojo.Carriage;
import com.tripoin.core.pojo.Image;
import com.tripoin.core.pojo.Menu;
import com.tripoin.core.pojo.OrderDetail;
import com.tripoin.core.pojo.Seat;
import com.tripoin.core.pojo.Train;
import com.tripoin.core.rest.client.IRestClient;
import com.tripoin.core.rest.sync.base.ASynchronize;
import com.tripoin.core.rest.util.RestConstant;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("synchronizeMenu")
public class SynchronizeMenuImpl extends ASynchronize {

	private static transient final Logger LOGGER = LoggerFactory.getLogger(SynchronizeMenuImpl.class);
	
	@Autowired
	private IRestClient restClientMenu;

	public String getNameVersion(){
		return RestConstant.VERSION_MASTER_MENU;
	}

	public String doSync(){
		String status = "Data Has Been Synchronize";
		List<Image> listImages = new ArrayList<Image>();
		List<OrderDetail> listDetails = new ArrayList<OrderDetail>();
		if(getDiffVersion() != 0){
			Menus menus = restClientMenu.execute(Menus.class);
			for(MenuDTO menuDTO : menus.getMaster_menu()){
				Menu menu = new Menu();
				menu.setCode(menuDTO.getMenu_code());
				menu.setImages(listImages);
				menu.setName(menuDTO.getMenu_name());
				menu.setOrderDetails(listDetails);
				menu.setPrice(menuDTO.getMenu_price());
				menu.setRating(menuDTO.getMenu_rating());
				menu.setStatus(1);
				menu.setStock(1);
				menu.setType(1);
				List<Menu> menuListCompare = iGenericManagerJpa.getObjectsUsingParameter(Menu.class, new String[]{"code"}, new Object[]{menuDTO.getMenu_code()}, null, null);
				if(menuListCompare.size() == 0){
					try {
						iGenericManagerJpa.saveObject(menu);
					} catch (Exception e) {
						LOGGER.error("Error Save Menu Synchronize : ".concat(e.getLocalizedMessage()));
					}
				}
			}
			try {
				version.setTimestamp(currentVersion);
				iGenericManagerJpa.updateObject(version);
			} catch (Exception e) {
				LOGGER.error("Error Update Data Menu : ".concat(e.getLocalizedMessage()));
			}
			status = "New Data";
		}
		return status;
	}
	
}
