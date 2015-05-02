package com.tripoin.rmu.model.DTO.carriage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;

import java.util.ArrayList;

/**
 * Created by Ridla on 4/18/2015.
 */
public class CarriageDTO extends BaseRESTDTO {

    @JsonProperty("master_carriage")
    private ArrayList<CarriageItemDTO> carriageItemDTOs;

    public ArrayList<CarriageItemDTO> getCarriageItemDTOs() {
        return carriageItemDTOs;
    }

    public void setCarriageItemDTOs(ArrayList<CarriageItemDTO> carriageItemDTOs) {
        this.carriageItemDTOs = carriageItemDTOs;
    }

    @Override
    public String toString() {
        return "CarriageDTO{" +
                "carriageItemDTOs=" + carriageItemDTOs +
                '}';
    }
}
