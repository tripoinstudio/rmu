package com.tripoin.rmu.model.DTO.train;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ridla on 4/18/2015.
 */
public class TrainItemDTO {

    @JsonProperty("train_code")
    private String trainCode;

    @JsonProperty("train_no")
    private String trainNo;

    @JsonProperty("train_remarks")
    private String trainRemaks;

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
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
                "trainCode='" + trainCode + '\'' +
                ", trainNo='" + trainNo + '\'' +
                ", trainRemaks='" + trainRemaks + '\'' +
                '}';
    }
}
