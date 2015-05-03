package com.tripoin.rmu.model.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */

@DatabaseTable( tableName = ModelConstant.MENU_TABLE )
public class OrderDetailModel {

    @DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.MENU_ID )
    private int id;

    @DatabaseField( columnName = ModelConstant.MENU_CODE )
    private String menuCode;

    @DatabaseField( columnName = ModelConstant.MENU_NAME )
    private String menuName;

    @DatabaseField( columnName = ModelConstant.MENU_TYPE )
    private String menuType;

    @DatabaseField( columnName = ModelConstant.MENU_PRICE )
    private String menuPrice;

    @DatabaseField( columnName = ModelConstant.MENU_RATING )
    private String menuRating;

    @DatabaseField( columnName = ModelConstant.MENU_IMAGE_URL )
    private String menuImageURL;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
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

    public String getMenuRating() {
        return menuRating;
    }

    public void setMenuRating(String menuRating) {
        this.menuRating = menuRating;
    }

    public String getMenuImageURL() {
        return menuImageURL;
    }

    public void setMenuImageURL(String menuImageURL) {
        this.menuImageURL = menuImageURL;
    }

    @Override
    public String toString() {
        return "MenuModel{" +
                "id=" + id +
                ", menuCode='" + menuCode + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuType='" + menuType + '\'' +
                ", menuPrice='" + menuPrice + '\'' +
                ", menuRating='" + menuRating + '\'' +
                ", menuImageURL='" + menuImageURL + '\'' +
                '}';
    }
}
