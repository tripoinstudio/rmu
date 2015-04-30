package com.tripoin.rmu.model.DTO.train;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;

import java.util.ArrayList;

/**
 * Created by Ridla on 4/18/2015.
 */
public class TrainDTO extends BaseRESTDTO {

    @JsonProperty("master_train")
    private ArrayList<TrainItemDTO> trainItemDTOs;

    public ArrayList<TrainItemDTO> getTrainItemDTOs() {
        return trainItemDTOs;
    }

    public void setTrainItemDTOs(ArrayList<TrainItemDTO> trainItemDTOs) {
        this.trainItemDTOs = trainItemDTOs;
    }

    @Override
    public String toString() {
        return "TrainDTO{" +
                "trainItemDTOs=" + trainItemDTOs +
                '}';
    }
}
