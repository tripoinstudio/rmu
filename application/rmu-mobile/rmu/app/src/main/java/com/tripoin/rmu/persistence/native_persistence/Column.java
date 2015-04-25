package com.tripoin.rmu.persistence.native_persistence;

import com.tripoin.rmu.persistence.enumeration.ColumnType;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 12/22/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Column DTO helper
 */
public class Column<T> implements Serializable{

    private String name;
    private T data;
    private ColumnType dataType;
    private boolean notNull;
    private boolean autoIncrement;
    private boolean primaryKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ColumnType getDataType() {
        return dataType;
    }

    public void setDataType(ColumnType dataType) {
        this.dataType = dataType;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
