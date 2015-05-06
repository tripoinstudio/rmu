package com.tripoin.rmu.model.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;

import java.io.Serializable;

/**
 * Created by bangkit on 5/5/2015.
 */

@DatabaseTable( tableName = ModelConstant.ORDER_TEMP_TABLE )
public class OrderTempModel{

    public OrderTempModel() {
    }

    @DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.ORDER_TEMP_ID )
    private int id;

    @DatabaseField( columnName = ModelConstant.ORDER_TEMP_MENU_ID )
    private String menuId;

    @DatabaseField( columnName = ModelConstant.ORDER_TEMP_QUANTITY )
    private String quantity;

    @DatabaseField( columnName = ModelConstant.ORDER_TEMP_PRICE )
    private String price;

    @DatabaseField( columnName = ModelConstant.ORDER_TEMP_CARRIAGE_ID )
    private String carriageId;

    @DatabaseField( columnName = ModelConstant.ORDER_TEMP_SEAT_ID )
    private String seatId;

    @DatabaseField( columnName = ModelConstant.ORDER_TEMP_TRAIN_ID )
    private String trainId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(String carriageId) {
        this.carriageId = carriageId;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return "OrderTempModel{" +
                "id=" + id +
                ", menuId='" + menuId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", carriageId='" + carriageId + '\'' +
                ", seatId='" + seatId + '\'' +
                ", trainId='" + trainId + '\'' +
                '}';
    }
}
