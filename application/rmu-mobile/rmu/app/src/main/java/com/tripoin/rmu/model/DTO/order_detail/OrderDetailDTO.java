package com.tripoin.rmu.model.DTO.order_detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;

import java.util.ArrayList;

/**
 * Created by Ridla on 4/19/2015.
 */
public class OrderDetailDTO extends BaseRESTDTO {

    @JsonProperty("trx_order_detail")
    private ArrayList<OrderDetailItemDTO> orderDetailItemDTOs;

    public ArrayList<OrderDetailItemDTO> getOrderDetailItemDTOs() {
        return orderDetailItemDTOs;
    }

    public void setOrderDetailItemDTOs(ArrayList<OrderDetailItemDTO> orderDetailItemDTOs) {
        this.orderDetailItemDTOs = orderDetailItemDTOs;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "orderDetailItemDTOs=" + orderDetailItemDTOs +
                '}';
    }
}
