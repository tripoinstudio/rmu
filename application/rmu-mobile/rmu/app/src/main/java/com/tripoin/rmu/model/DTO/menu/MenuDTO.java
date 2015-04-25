package com.tripoin.rmu.model.DTO.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.DTO.BaseRESTDTO;

import java.util.ArrayList;

/**
 * Created by Ridla on 4/18/2015.
 */
public class MenuDTO extends BaseRESTDTO{

    @JsonProperty("master_menu")
    private ArrayList<MenuItemDTO> menuItemDTOs;

    public ArrayList<MenuItemDTO> getMenuItemDTOs() {
        return menuItemDTOs;
    }

    public void setMenuItemDTOs(ArrayList<MenuItemDTO> menuItemDTOs) {
        this.menuItemDTOs = menuItemDTOs;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuItemDTOs=" + menuItemDTOs +
                '}';
    }
}
