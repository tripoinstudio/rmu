package com.tripoin.rmu.model.DTO.seat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.DTO.BaseRESTDTO;

/**
 * Created by Ridla on 4/18/2015.
 */
public class SeatItemDTO{

    @JsonProperty("seat_id")
    private String seatId;

    @JsonProperty("seat_no")
    private String seatNo;

    @JsonProperty("seat_remarks")
    private String seatRemarks;

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
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
                "seatId='" + seatId + '\'' +
                ", seatNo='" + seatNo + '\'' +
                ", seatRemarks='" + seatRemarks + '\'' +
                '}';
    }
}
