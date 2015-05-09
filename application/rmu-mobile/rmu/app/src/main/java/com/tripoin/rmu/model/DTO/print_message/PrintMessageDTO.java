package com.tripoin.rmu.model.DTO.print_message;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Achmad Fauzi on 5/7/2015 : 10:18 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class PrintMessageDTO implements Parcelable{

    private String orderNo;
    private String total;
    private ArrayList<MessageItemDTO> messageItemDTOs;

    public PrintMessageDTO() {
    }

    public PrintMessageDTO(Parcel in) {
        readFromParcel(in);
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<MessageItemDTO> getMessageItemDTOs() {
        return messageItemDTOs;
    }

    public void setMessageItemDTOs(ArrayList<MessageItemDTO> messageItemDTOs) {
        this.messageItemDTOs = messageItemDTOs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderNo);
        dest.writeString(total);
        dest.writeList(messageItemDTOs);
    }

    private void readFromParcel(Parcel in){
        orderNo = in.readString();
        total = in.readString();
        messageItemDTOs = in.readArrayList(MessageItemDTO.class.getClassLoader());
    }
}
