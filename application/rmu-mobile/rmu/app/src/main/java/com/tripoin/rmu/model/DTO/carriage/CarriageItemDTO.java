package com.tripoin.rmu.model.DTO.carriage;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Dayat on 4/18/2015.
 */
public class CarriageItemDTO {

    @JsonProperty("carriage_id")
    private String carriageId;

    @JsonProperty("carriage_no")
    private String carriageNo;

    @JsonProperty("carriage_remarks")
    private String carriageRemarks;

    public String getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(String carriageId) {
        this.carriageId = carriageId;
    }

    public String getCarriageNo() {
        return carriageNo;
    }

    public void setCarriageNo(String carriageNo) {
        this.carriageNo = carriageNo;
    }

    public String getCarriageRemarks() {
        return carriageRemarks;
    }

    public void setCarriageRemarks(String carriageRemarks) {
        this.carriageRemarks = carriageRemarks;
    }

    @Override
    public String toString() {
        return "CarriageItemDTO{" +
                "carriageId='" + carriageId + '\'' +
                ", carriageNo='" + carriageNo + '\'' +
                ", carriageRemarks='" + carriageRemarks + '\'' +
                '}';
    }
}
