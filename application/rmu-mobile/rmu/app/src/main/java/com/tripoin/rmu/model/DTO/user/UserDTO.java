package com.tripoin.rmu.model.DTO.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.DTO.BaseRESTDTO;
import com.tripoin.rmu.model.DTO.order_header.OrderHeaderItemDTO;

import java.util.ArrayList;

/**
 * Created by Ridla on 4/18/2015.
 */
public class UserDTO extends BaseRESTDTO {

    @JsonProperty("security_user")
    private UserItemDTO userItemDTOs;

    @JsonProperty("trx_order_header")
    private ArrayList<OrderHeaderItemDTO> orderHeaderItemDTOs;

    public UserItemDTO getUserItemDTOs() {
        return userItemDTOs;
    }

    public void setUserItemDTOs(UserItemDTO userItemDTOs) {
        this.userItemDTOs = userItemDTOs;
    }

    public ArrayList<OrderHeaderItemDTO> getOrderHeaderItemDTOs() {
        return orderHeaderItemDTOs;
    }

    public void setOrderHeaderItemDTOs(ArrayList<OrderHeaderItemDTO> orderHeaderItemDTOs) {
        this.orderHeaderItemDTOs = orderHeaderItemDTOs;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userItemDTOs=" + userItemDTOs +
                ", orderHeaderItemDTOs=" + orderHeaderItemDTOs +
                '}';
    }
}
