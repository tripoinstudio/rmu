package com.tripoin.rmu.model.DTO.order_detail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ridla on 4/19/2015.
 */
public class OrderDetailItemDTO {

    @JsonProperty("order_header_no")
    private String orderHeaderNo;

    @JsonProperty("order_detail_total_order")
    private String orderDetailTotalOrder;

    @JsonProperty("order_detail_total_amount")
    private String orderDetailTotalAmount;

    @JsonProperty("order_header_status")
    private String orderHeaderStatus;

    @JsonProperty("menu_code")
    private String menuCode;

    @JsonProperty("menu_name")
    private String menuName;

    @JsonProperty("seat_code")
    private String seatCode;

    @JsonProperty("carriage_code")
    private String carriageCode;

    @JsonProperty("train_code")
    private String trainCode;

    public String getOrderHeaderNo() {
        return orderHeaderNo;
    }

    public void setOrderHeaderNo(String orderHeaderNo) {
        this.orderHeaderNo = orderHeaderNo;
    }

    public String getOrderDetailTotalOrder() {
        return orderDetailTotalOrder;
    }

    public void setOrderDetailTotalOrder(String orderDetailTotalOrder) {
        this.orderDetailTotalOrder = orderDetailTotalOrder;
    }

    public String getOrderDetailTotalAmount() {
        return orderDetailTotalAmount;
    }

    public void setOrderDetailTotalAmount(String orderDetailTotalAmount) {
        this.orderDetailTotalAmount = orderDetailTotalAmount;
    }

    public String getOrderHeaderStatus() {
        return orderHeaderStatus;
    }

    public void setOrderHeaderStatus(String orderHeaderStatus) {
        this.orderHeaderStatus = orderHeaderStatus;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getCarriageCode() {
        return carriageCode;
    }

    public void setCarriageCode(String carriageCode) {
        this.carriageCode = carriageCode;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    public String toString() {
        return "OrderDetailItemDTO{" +
                "orderHeaderNo='" + orderHeaderNo + '\'' +
                ", orderDetailTotalOrder='" + orderDetailTotalOrder + '\'' +
                ", orderDetailTotalAmount='" + orderDetailTotalAmount + '\'' +
                ", orderHeaderStatus='" + orderHeaderStatus + '\'' +
                ", menuCode='" + menuCode + '\'' +
                ", menuName='" + menuName + '\'' +
                ", seatCode='" + seatCode + '\'' +
                ", carriageCode='" + carriageCode + '\'' +
                ", trainCode='" + trainCode + '\'' +
                '}';
    }
}
