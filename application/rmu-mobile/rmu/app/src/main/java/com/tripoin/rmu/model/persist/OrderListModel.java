package com.tripoin.rmu.model.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 12:58 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@DatabaseTable( tableName = ModelConstant.ORDER_LIST_TABLE )
public class OrderListModel {

    public OrderListModel() {
    }

    /*@DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.ORDER_LIST_ID )
    private int id;*/

    @DatabaseField( id = true, columnName = ModelConstant.ORDER_LIST_ORDER_ID, canBeNull = false)
    private String orderId;

    @DatabaseField( columnName = ModelConstant.ORDER_LIST_CARRIAGE_NUMBER )
    private String carriageNumber;

    @DatabaseField( columnName = ModelConstant.ORDER_LIST_SEAT_NUMBER )
    private String seatNumber;

    @DatabaseField( columnName = ModelConstant.ORDER_LIST_TOTAL_PAID )
    private String totalPaid;

    @DatabaseField( columnName = ModelConstant.ORDER_LIST_ORDER_TIME )
    private String orderTime;

    @DatabaseField( columnName = ModelConstant.ORDER_LIST_PROCESS_STATUS )
    private int processStatus;

    @DatabaseField( columnName = ModelConstant.ORDER_LIST_WAITING_STATUS )
    private int waitingStatus;

/*    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

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

    public int getWaitingStatus() {
        return waitingStatus;
    }

    public void setWaitingStatus(int waitingStatus) {
        this.waitingStatus = waitingStatus;
    }

    @Override
    public String toString() {
        return "OrderListModel{" +
                /*"id=" + id +*/
                ", orderId='" + orderId + '\'' +
                ", carriageNumber='" + carriageNumber + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", totalPaid='" + totalPaid + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", processStatus=" + processStatus +
                ", waitingStatus=" + waitingStatus +
                '}';
    }
}
