package com.tripoin.rmu.model.DTO.train;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ridla on 4/18/2015.
 */
public class TrainItemDTO {

    @JsonProperty("train_id")
    private String trainId;

    @JsonProperty("train_no")
    private String trainNo;

    @JsonProperty("train_remarks")
    private String trainRemaks;

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainRemaks() {
        return trainRemaks;
    }

    public void setTrainRemaks(String trainRemaks) {
        this.trainRemaks = trainRemaks;
    }

    @Override
    public String toString() {
        return "TrainItemDTO{" +
                "trainId='" + trainId + '\'' +
                ", trainNo='" + trainNo + '\'' +
                ", trainRemaks='" + trainRemaks + '\'' +
                '}';
    }
}
