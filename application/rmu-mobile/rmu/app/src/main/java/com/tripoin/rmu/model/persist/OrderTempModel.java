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

    @DatabaseField( columnName = ModelConstant.MENU_CODE )
    private String menuCode;

    @DatabaseField( columnName = ModelConstant.MENU_NAME )
    private String menuName;

    @DatabaseField( columnName = ModelConstant.ORDER_TEMP_QUANTITY )
    private String quantity;

    @DatabaseField( columnName = ModelConstant.ORDER_TEMP_PRICE )
    private String price;

    @DatabaseField( columnName = ModelConstant.CARRIAGE_CODE )
    private String carriageCode;

    @DatabaseField( columnName = ModelConstant.SEAT_CODE )
    private String seatCode;

    @DatabaseField( columnName = ModelConstant.TRAIN_CODE )
    private String trainCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCarriageCode() {
        return carriageCode;
    }

    public void setCarriageCode(String carriageCode) {
        this.carriageCode = carriageCode;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    @Override
    public String toString() {
        return "OrderTempModel{" +
                "id=" + id +
                ", menuCode='" + menuCode + '\'' +
                ", menuName='" + menuName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", carriageCode='" + carriageCode + '\'' +
                ", seatCode='" + seatCode + '\'' +
                ", trainCode='" + trainCode + '\'' +
                '}';
    }
}
