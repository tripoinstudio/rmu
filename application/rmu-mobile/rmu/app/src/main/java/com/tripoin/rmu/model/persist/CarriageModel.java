package com.tripoin.rmu.model.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;

/**
 * Created by bangkit on 5/2/2015.
 */
@DatabaseTable( tableName = ModelConstant.CARRIAGE_TABLE )
public class CarriageModel {

    @DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.CARRIAGE_ID )
    private int id;

    @DatabaseField( columnName = ModelConstant.CARRIAGE_CODE )
    private String carriageCode;

    @DatabaseField( columnName = ModelConstant.CARRIAGE_NO )
    private String carriageNo;

    @DatabaseField( columnName = ModelConstant.CARRIAGE_REMARKS )
    private String carriageRemarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarriageCode() {
        return carriageCode;
    }

    public void setCarriageCode(String carriageCode) {
        this.carriageCode = carriageCode;
    }

    public String getCarriageNo() {
        return carriageNo;
    }

    public void setCarriageNo(String carriageNo) {
        this.carriageNo = carriageNo;
    }

    public String getCarriageRemarks() {
        return carriageRemarks;
    }

    public void setCarriageRemarks(String carriageRemarks) {
        this.carriageRemarks = carriageRemarks;
    }

    @Override
    public String toString() {
        return "CarriageModel{" +
                "id=" + id +
                ", carriageCode='" + carriageCode + '\'' +
                ", carriageNo='" + carriageNo + '\'' +
                ", carriageRemarks='" + carriageRemarks + '\'' +
                '}';
    }
}
