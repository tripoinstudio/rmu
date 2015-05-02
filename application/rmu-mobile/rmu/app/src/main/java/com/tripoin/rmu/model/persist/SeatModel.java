package com.tripoin.rmu.model.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;

/**
 * Created by bangkit on 5/2/2015.
 */
@DatabaseTable( tableName = ModelConstant.SEAT_TABLE )
public class SeatModel {

    @DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.SEAT_ID )
    private int id;

    @DatabaseField( columnName = ModelConstant.SEAT_CODE )
    private String seatCode;

    @DatabaseField( columnName = ModelConstant.SEAT_NO )
    private String seatNo;

    @DatabaseField( columnName = ModelConstant.SEAT_REMARKS )
    private String seatRemarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getSeatRemarks() {
        return seatRemarks;
    }

    public void setSeatRemarks(String seatRemarks) {
        this.seatRemarks = seatRemarks;
    }

    @Override
    public String toString() {
        return "SeatModel{" +
                "id=" + id +
                ", seatCode='" + seatCode + '\'' +
                ", seatNo='" + seatNo + '\'' +
                ", seatRemarks='" + seatRemarks + '\'' +
                '}';
    }
}

