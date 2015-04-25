package com.tripoin.rmu.model.DTO.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by Ridla on 4/18/2015.
 */
public class MenuItemDTO {

    @JsonProperty("menu_id")
    private String menuId;

    @JsonProperty("menu_name")
    private String menuName;

    @JsonProperty("menu_type")
    private String menuType;

    @JsonProperty("menu_price")
    private String menuPrice;

    @JsonProperty("menu_image_url")
    private String menuImage;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    @Override
    public String toString() {
        return "MenuItemDTO{" +
                "menuId='" + menuId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuType='" + menuType + '\'' +
                ", menuPrice='" + menuPrice + '\'' +
                ", menuImage='" + menuImage + '\'' +
                '}';
    }
}
