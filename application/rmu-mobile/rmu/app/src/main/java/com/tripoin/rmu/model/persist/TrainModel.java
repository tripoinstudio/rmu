package com.tripoin.rmu.model.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;

/**
 * Created by bangkit on 5/2/2015.
 */
@DatabaseTable( tableName = ModelConstant.TRAIN_TABLE )
public class TrainModel {
    @DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.TRAIN_ID )
    private int id;

    @DatabaseField( columnName = ModelConstant.TRAIN_CODE )
    private String trainCode;

    @DatabaseField( columnName = ModelConstant.TRAIN_NO )
    private String trainNo;

    @DatabaseField( columnName = ModelConstant.TRAIN_REMARKS )
    private String trainRemarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getTrainRemarks() {
        return trainRemarks;
    }

    public void setTrainRemarks(String trainRemarks) {
        this.trainRemarks = trainRemarks;
    }

    @Override
    public String toString() {
        return "TrainModel{" +
                "id=" + id +
                ", trainCode='" + trainCode + '\'' +
                ", trainNo='" + trainNo + '\'' +
                ", trainRemarks='" + trainRemarks + '\'' +
                '}';
    }
}
