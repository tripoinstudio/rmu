package com.tripoin.rmu.model.DTO.carriage;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Dayat on 4/18/2015.
 */
public class CarriageItemDTO {

    @JsonProperty("carriage_code")
    private String carriageCode;

    @JsonProperty("carriage_no")
    private String carriageNo;

    @JsonProperty("carriage_remarks")
    private String carriageRemarks;

    public String getCarriageCode() {
        return carriageCode;
    }

    public void setCarriageCode(String carriageCode) {
        this.carriageCode = carriageCode;
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
                "carriageCode='" + carriageCode + '\'' +
                ", carriageNo='" + carriageNo + '\'' +
                ", carriageRemarks='" + carriageRemarks + '\'' +
                '}';
    }
}
