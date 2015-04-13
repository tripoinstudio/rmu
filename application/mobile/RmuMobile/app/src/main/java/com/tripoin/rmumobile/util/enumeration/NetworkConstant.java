package com.tripoin.rmumobile.util.enumeration;

/**
 * Created by Achmad Fauzi on 12/30/2014.
 * achmad.fauzi@sigma.co.id
 */
public enum NetworkConstant {

    TYPE_WIFI("1"),
    TYPE_MOBILE("2"),
    TYPE_NOT_CONNECTED("0"),
    WIFI_ENABLED("Wifi enabled"),
    MOBILE_DATA_ENABLED("Mobile data enabled"),
    NOT_CONNECTED_TO_INTERNET("Not connected to Internet"),
    WIFI("WIFI"),
    PHONE_STATE_IDLE("IDLE"),
    PHONE_STATE_OFF_HOOK("OFF HOOK"),
    PHONE_STATE_RINGING("RINGING");


    private String internalValue;

    private NetworkConstant( String code ){
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
