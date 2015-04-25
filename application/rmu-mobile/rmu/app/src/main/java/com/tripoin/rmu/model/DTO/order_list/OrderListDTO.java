package com.tripoin.rmu.model.DTO.order_list;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 5:55 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class OrderListDTO {

    private String orderId;
    private String carriageNumber;
    private String seatNumber;
    private String totalPaid;
    private String orderTime;
    private int processStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(String carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public String toString() {
        return "OrderListDTO{" +
                "orderId='" + orderId + '\'' +
                ", carriageNumber='" + carriageNumber + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", totalPaid='" + totalPaid + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", processStatus=" + processStatus +
                '}';
    }
}
