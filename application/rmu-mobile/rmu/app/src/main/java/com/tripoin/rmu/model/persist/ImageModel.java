package com.tripoin.rmu.model.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */

@DatabaseTable( tableName = ModelConstant.IMAGE_TABLE )
public class ImageModel {

    @DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.IMAGE_ID )
    private int id;

    @DatabaseField( columnName = ModelConstant.IMAGE_CODE )
    private String imageCode;

    @DatabaseField( columnName = ModelConstant.IMAGE_NAME )
    private String imageName;

    @DatabaseField( columnName = ModelConstant.IMAGE_STATUS )
    private String imageStatus;

    @DatabaseField( columnName = ModelConstant.MENU_ID )
    private int menuId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "id=" + id +
                ", imageCode='" + imageCode + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageStatus='" + imageStatus + '\'' +
                ", menuId='" + menuId + '\'' +
                '}';
    }
}
