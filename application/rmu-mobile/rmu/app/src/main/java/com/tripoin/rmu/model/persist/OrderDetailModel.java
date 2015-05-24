package com.tripoin.rmu.model.persist;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 7:58 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
@DatabaseTable( tableName = ModelConstant.ORDER_DETAIL_TABLE)
public class OrderDetailModel implements Parcelable{

    @DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.ORDER_DETAIL_ID)
    private int id;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_ORDER_HEADER_NO)
    private String orderHeaderNo;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_ORDER_DETAIL_TOTAL_ORDER )
    private String orderDetailTotalOrder;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_ORDER_DETAIL_TOTAL_AMOUNT )
    private String orderDetailTotalAmount;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_ORDER_HEADER_STATUS )
    private String orderHeaderStatus;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_ORDER_HEADER_STATUS_WAITING )
    private String orderHeaderStatusWaiting;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_MENU_CODE )
    private String menuCode;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_MENU_NAME )
    private String menuName;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_SEAT_CODE )
    private String seatCode;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_CARRIAGE_CODE)
    private String carriageCode;

    @DatabaseField( columnName = ModelConstant.ORDER_DETAIL_TRAIN_CODE)
    private String trainCode;

    public OrderDetailModel(Parcel parcel) {
        readFromParcel(parcel);
    }

    public OrderDetailModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getOrderHeaderStatusWaiting() {
        return orderHeaderStatusWaiting;
    }

    public void setOrderHeaderStatusWaiting(String orderHeaderStatusWaiting) {
        this.orderHeaderStatusWaiting = orderHeaderStatusWaiting;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
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

    @Override
    public String toString() {
        return "DetailOrderModel{" +
                "id=" + id +
                ", orderHeaderNo='" + orderHeaderNo + '\'' +
                ", orderDetailTotalOrder='" + orderDetailTotalOrder + '\'' +
                ", orderDetailTotalAmount='" + orderDetailTotalAmount + '\'' +
                ", orderHeaderStatus='" + orderHeaderStatus + '\'' +
                ", orderHeaderStatusWaiting='" + orderHeaderStatusWaiting + '\'' +
                ", menuCode='" + menuCode + '\'' +
                ", menuName='" + menuName + '\'' +
                ", seatCode='" + seatCode + '\'' +
                ", carriageCode='" + carriageCode + '\'' +
                ", trainCode='" + trainCode + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(orderHeaderNo);
        dest.writeString(orderDetailTotalOrder);
        dest.writeString(orderDetailTotalAmount);
        dest.writeString(orderHeaderStatus);
        dest.writeString(orderHeaderStatusWaiting);
        dest.writeString(menuCode);
        dest.writeString(menuName);
        dest.writeString(seatCode);
        dest.writeString(carriageCode);
        dest.writeString(trainCode);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        orderHeaderNo = in.readString();
        orderDetailTotalOrder = in.readString();
        orderDetailTotalAmount = in.readString();
        orderHeaderStatus = in.readString();
        orderHeaderStatusWaiting = in.readString();
        menuCode = in.readString();
        menuName = in.readString();
        seatCode = in.readString();
        carriageCode = in.readString();
        trainCode = in.readString();
    }


}
