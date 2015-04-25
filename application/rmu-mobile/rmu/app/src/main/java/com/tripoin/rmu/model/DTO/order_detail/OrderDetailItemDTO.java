package com.tripoin.rmu.model.DTO.order_detail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ridla on 4/19/2015.
 */
public class OrderDetailItemDTO {

    @JsonProperty("order_header_no")
    private String orderHeaderNo;

    @JsonProperty("order_detail_total_order")
    private String ord_detail_total_order;

    @JsonProperty("order_detail_total_amount")
    private String ord_detail_total_amount;

    @JsonProperty("order_header_status")
    private String orderHeaderStatus;

    @JsonProperty("user_code")
    private String userCode;

    @JsonProperty("menu_id")
    private String menuId;

    @JsonProperty("menu_name")
    private String menu_name;

    @JsonProperty("seat_id")
    private String seatId;

    @JsonProperty("carriage_id")
    private String carriageId;

    @JsonProperty("train_id")
    private String trainId;

    public String getOrderHeaderNo() {
        return orderHeaderNo;
    }

    public void setOrderHeaderNo(String orderHeaderNo) {
        this.orderHeaderNo = orderHeaderNo;
    }

    public String getOrd_detail_total_order() {
        return ord_detail_total_order;
    }

    public void setOrd_detail_total_order(String ord_detail_total_order) {
        this.ord_detail_total_order = ord_detail_total_order;
    }

    public String getOrd_detail_total_amount() {
        return ord_detail_total_amount;
    }

    public void setOrd_detail_total_amount(String ord_detail_total_amount) {
        this.ord_detail_total_amount = ord_detail_total_amount;
    }

    public String getOrderHeaderStatus() {
        return orderHeaderStatus;
    }

    public void setOrderHeaderStatus(String orderHeaderStatus) {
        this.orderHeaderStatus = orderHeaderStatus;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(String carriageId) {
        this.carriageId = carriageId;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return "OrderDetailItemDTO{" +
                "orderHeaderNo='" + orderHeaderNo + '\'' +
                ", ord_detail_total_order='" + ord_detail_total_order + '\'' +
                ", ord_detail_total_amount='" + ord_detail_total_amount + '\'' +
                ", orderHeaderStatus='" + orderHeaderStatus + '\'' +
                ", userCode='" + userCode + '\'' +
                ", menuId='" + menuId + '\'' +
                ", menu_name='" + menu_name + '\'' +
                ", seatId='" + seatId + '\'' +
                ", carriageId='" + carriageId + '\'' +
                ", trainId='" + trainId + '\'' +
                '}';
    }
}
