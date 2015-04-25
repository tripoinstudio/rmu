package com.tripoin.rmu.model.DTO.order_header;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Dayat on 4/18/2015.
 */
public class OrderHeaderItemDTO {

    @JsonProperty("order_header_no")
    private String orderHeaderNo;

    @JsonProperty("order_header_order_datetime")
    private String orderHeaderDateTime;

    @JsonProperty("order_header_total_paid")
    private String orderTotalPaid;

    @JsonProperty("order_header_status")
    private String orderHeaderStatus;

    @JsonProperty("seat_no")
    private String seatNo;

    @JsonProperty("carriage_no")
    private String carriageNo;

    @JsonProperty("train_no")
    private String trainNo;

    public String getOrderHeaderNo() {
        return orderHeaderNo;
    }

    public void setOrderHeaderNo(String orderHeaderNo) {
        this.orderHeaderNo = orderHeaderNo;
    }

    public String getOrderHeaderDateTime() {
        return orderHeaderDateTime;
    }

    public void setOrderHeaderDateTime(String orderHeaderDateTime) {
        this.orderHeaderDateTime = orderHeaderDateTime;
    }

    public String getOrderTotalPaid() {
        return orderTotalPaid;
    }

    public void setOrderTotalPaid(String orderTotalPaid) {
        this.orderTotalPaid = orderTotalPaid;
    }

    public String getOrderHeaderStatus() {
        return orderHeaderStatus;
    }

    public void setOrderHeaderStatus(String orderHeaderStatus) {
        this.orderHeaderStatus = orderHeaderStatus;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getCarriageNo() {
        return carriageNo;
    }

    public void setCarriageNo(String carriageNo) {
        this.carriageNo = carriageNo;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    @Override
    public String toString() {
        return "OrderHeaderItemDTO{" +
                "orderHeaderNo='" + orderHeaderNo + '\'' +
                ", orderHeaderDateTime='" + orderHeaderDateTime + '\'' +
                ", orderTotalPaid='" + orderTotalPaid + '\'' +
                ", orderHeaderStatus='" + orderHeaderStatus + '\'' +
                ", seatNo='" + seatNo + '\'' +
                ", carriageNo='" + carriageNo + '\'' +
                ", trainNo='" + trainNo + '\'' +
                '}';
    }
}
