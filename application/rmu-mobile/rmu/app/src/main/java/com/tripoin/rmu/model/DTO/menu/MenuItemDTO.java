package com.tripoin.rmu.model.DTO.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.DTO.image.ImageItemDTO;

import java.util.List;

/**
 * Created by Ridla on 4/18/2015.
 */
public class MenuItemDTO {


    @JsonProperty("menu_name")
    private String menuName;

    @JsonProperty("menu_code")
    private String menuCode;

    @JsonProperty("menu_type")
    private String menuType;

    @JsonProperty("menu_price")
    private String menuPrice;

    @JsonProperty("menu_stock")
    private String menuStock;

    @JsonProperty("menu_image_url")
    private String menuImage;

    @JsonProperty("menu_rating")
    private String menuRating;

    @JsonProperty("master_image")
    private List<ImageItemDTO> imageItemDTOList;

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

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuRating() {
        return menuRating;
    }

    public void setMenuRating(String menuRating) {
        this.menuRating = menuRating;
    }

    public String getMenuStock() { return menuStock; }

    public void setMenuStock(String menuStock) { this.menuStock = menuStock; }

    public List<ImageItemDTO> getImageItemDTOList() { return imageItemDTOList; }

    public void setImageItemDTOList(List<ImageItemDTO> imageItemDTOList) { this.imageItemDTOList = imageItemDTOList; }

    @Override
    public String toString() {
        return "MenuItemDTO{" +
                "menuName='" + menuName + '\'' +
                ", menuCode='" + menuCode + '\'' +
                ", menuType='" + menuType + '\'' +
                ", menuPrice='" + menuPrice + '\'' +
                ", menuStock='" + menuStock + '\'' +
                ", menuImage='" + menuImage + '\'' +
                ", menuRating='" + menuRating + '\'' +
                ", imageItemDTOList=" + imageItemDTOList +
                '}';
    }
}
