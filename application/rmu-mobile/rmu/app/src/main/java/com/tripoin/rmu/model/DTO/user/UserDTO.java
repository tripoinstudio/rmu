package com.tripoin.rmu.model.DTO.user;

import android.os.Parcel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;
import com.tripoin.rmu.model.DTO.master.MasterVersionItem;
import com.tripoin.rmu.model.DTO.order_header.OrderHeaderItemDTO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ridla on 4/18/2015.
 */
public class UserDTO extends BaseRESTDTO /*implements Parcelable*/ implements Serializable{

    @JsonProperty("security_user")
    private UserItemDTO userItemDTO;

    @JsonProperty("trx_order_header")
    private ArrayList<OrderHeaderItemDTO> orderHeaderItemDTOs;

    @JsonProperty("master_version")
    private ArrayList<MasterVersionItem> masterVersionItems;

    /*public UserDTO(Parcel in) {
        readFromParcel(in);
    }*/

    public UserDTO() {
    }

    public UserItemDTO getUserItemDTO() {
        return userItemDTO;
    }

    public void setUserItemDTO(UserItemDTO userItemDTO) {
        this.userItemDTO = userItemDTO;
    }

    public ArrayList<OrderHeaderItemDTO> getOrderHeaderItemDTOs() {
        return orderHeaderItemDTOs;
    }

    public void setOrderHeaderItemDTOs(ArrayList<OrderHeaderItemDTO> orderHeaderItemDTOs) {
        this.orderHeaderItemDTOs = orderHeaderItemDTOs;
    }

    public ArrayList<MasterVersionItem> getMasterVersionItems() {
        return masterVersionItems;
    }

    public void setMasterVersionItems(ArrayList<MasterVersionItem> masterVersionItems) {
        this.masterVersionItems = masterVersionItems;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "masterVersions=" + masterVersionItems +
                ", orderHeaderItemDTOs=" + orderHeaderItemDTOs +
                ", userItemDTO=" + userItemDTO +
                '}';
    }

/*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userItemDTO);
        dest.writeList(orderHeaderItemDTOs);
    }*/

    private void readFromParcel(Parcel in) {
        //userItemDTO = in.readParcelable();
    }

    /*Creator creator = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new UserDTO(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new UserDTO[size];
        }
    };*/



}
