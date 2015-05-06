package com.tripoin.rmu.model.DTO.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;

import java.util.ArrayList;

/**
 * Created by Ridla on 4/18/2015.
 */
public class ImageDTO extends BaseRESTDTO{

    @JsonProperty("master_image")
    private ArrayList<ImageItemDTO> imageItemDTOs;

    public ArrayList<ImageItemDTO> getImageItemDTOs() {
        return imageItemDTOs;
    }

    public void setImageItemDTOs(ArrayList<ImageItemDTO> imageItemDTOs) {
        this.imageItemDTOs = imageItemDTOs;
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "imageItemDTOs=" + imageItemDTOs +
                '}';
    }
}
