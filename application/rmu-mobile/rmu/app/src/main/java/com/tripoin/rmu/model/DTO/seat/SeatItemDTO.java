package com.tripoin.rmu.model.DTO.seat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ridla on 4/18/2015.
 */
public class SeatItemDTO{

    @JsonProperty("seat_code")
    private String seatCode;

    @JsonProperty("seat_no")
    private String seatNo;

    @JsonProperty("seat_remarks")
    private String seatRemarks;

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getSeatRemarks() {
        return seatRemarks;
    }

    public void setSeatRemarks(String seatRemarks) {
        this.seatRemarks = seatRemarks;
    }

    @Override
    public String toString() {
        return "SeatItemDTO{" +
                "seatCode='" + seatCode + '\'' +
                ", seatNo='" + seatNo + '\'' +
                ", seatRemarks='" + seatRemarks + '\'' +
                '}';
    }
}
