package com.tripoin.rmu.model.DTO.image;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ridla on 4/18/2015.
 */
public class ImageItemDTO {


    @JsonProperty("image_code")
    private String imageCode;

    @JsonProperty("image_name")
    private String imageName;

    @JsonProperty("image_status")
    private String imageStatus;

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

    @Override
    public String toString() {
        return "MenuItemDTO{" +
                "imageCode='" + imageCode + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageStatus='" + imageStatus + '\'' +
                '}';
    }
}
