package com.tripoin.rmu.persistence.enumeration;


/**
 * Created by Achmad Fauzi on 1/24/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public enum DatabaseConstant {
    //DB_NAME( PropertyConstant.PROPERTIES_PATH.toString().concat("mforce_mqa.db") ),
    DB_NAME( "rmu_mobile.db" ),
    DB_VERSION("1");

    private String internalValue;

    private DatabaseConstant(String code){
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
