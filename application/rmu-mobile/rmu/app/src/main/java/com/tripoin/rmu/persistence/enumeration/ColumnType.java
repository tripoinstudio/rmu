package com.tripoin.rmu.persistence.enumeration;

/**
 * Created by Achmad Fauzi on 12/22/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */
public enum ColumnType {

    INTEGER("integer") ,
    TEXT("text") ,
    REAL("real") ,
    BLOB("blob");

    private String internalValue;

    private ColumnType( String code ){
        this.internalValue = code;
    }

    public String getInternalValue() {
        return this.internalValue;
    }

    @Override
    public String toString() {
        return this.internalValue;
    }
}
