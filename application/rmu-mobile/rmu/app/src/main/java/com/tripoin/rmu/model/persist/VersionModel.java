package com.tripoin.rmu.model.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */

@DatabaseTable( tableName = ModelConstant.VERSION_TABLE )
public class VersionModel {

    @DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.VERSION_ID )
    private int id;

    @DatabaseField( columnName = ModelConstant.VERSION_NAMETABLE )
    private String versionNameTable;

    @DatabaseField( columnName = ModelConstant.VERSION_TIMESTAMP_OLD )
    private String versionTimestampOld;

    @DatabaseField( columnName = ModelConstant.VERSION_TIMESTAMP_NEW )
    private String versionTimestampNew;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionNameTable() {
        return versionNameTable;
    }

    public void setVersionNameTable(String versionNameTable) {
        this.versionNameTable = versionNameTable;
    }

    public String getVersionTimestampOld() {
        return versionTimestampOld;
    }

    public void setVersionTimestampOld(String versionTimestampOld) {
        this.versionTimestampOld = versionTimestampOld;
    }

    public String getVersionTimestampNew() {
        return versionTimestampNew;
    }

    public void setVersionTimestampNew(String versionTimestampNew) {
        this.versionTimestampNew = versionTimestampNew;
    }

    @Override
    public String toString() {
        return "VersionModel{" +
                "id=" + id +
                ", versionNameTable='" + versionNameTable + '\'' +
                ", versionTimestampOld='" + versionTimestampOld + '\'' +
                ", versionTimestampNew='" + versionTimestampNew + '\'' +
                '}';
    }
}
