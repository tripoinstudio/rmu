package com.tripoin.rmu.model.DTO.order_header;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;

import java.util.ArrayList;

/**
 * Created by Ridla on 4/18/2015.
 */
public class OrderHeaderDTO extends BaseRESTDTO {

    @JsonProperty("trx_order_header")
    private ArrayList<OrderHeaderItemDTO> orderHeaderItemDTOs;

    public ArrayList<OrderHeaderItemDTO> getOrderHeaderItemDTOs() {
        return orderHeaderItemDTOs;
    }

    public void setOrderHeaderItemDTOs(ArrayList<OrderHeaderItemDTO> orderHeaderItemDTOs) {
        this.orderHeaderItemDTOs = orderHeaderItemDTOs;
    }

    @Override
    public String toString() {
        return "OrderHeaderDTO{" +
                "orderHeaderItemDTOs=" + orderHeaderItemDTOs +
                '}';
    }
}
