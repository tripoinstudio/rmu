package com.tripoin.rmu.model.DTO.seat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;

import java.util.ArrayList;

/**
 * Created by Ridla on 4/18/2015.
 */
public class SeatDTO extends BaseRESTDTO{

    @JsonProperty("master_seat")
    private ArrayList<SeatItemDTO> seatItemDTOs;

    public ArrayList<SeatItemDTO> getSeatItemDTOs() {
        return seatItemDTOs;
    }

    public void setSeatItemDTOs(ArrayList<SeatItemDTO> seatItemDTOs) {
        this.seatItemDTOs = seatItemDTOs;
    }

    @Override
    public String toString() {
        return "SeatDTO{" +
                "seatItemDTOs=" + seatItemDTOs +
                '}';
    }
}
