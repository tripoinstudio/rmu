package com.tripoin.rmu.view.enumeration;

/**
 * Created by Achmad Fauzi on 12/30/2014.
 * achmad.fauzi@sigma.co.id
 *
 * All Strings for UI message as Constant
 */
public enum ViewConstant {

    FONT_ADAM("font/ADAM.CG PRO.otf"),
    FONT_BGOTHL("font/Bgothl.ttf"),
    FONT_OPEN_SANS_LIGHT("font/OpenSans-Light.ttf"),
    FONT_UBUNTU("font/Ubuntu-R.ttf"),

    ACTION_BAR_TITLE("eRestorasi"),

    ERROR("ERROR"),
    ZERO("0"),
    ONE("1"),
    SUCCESS("SUCCESS"),
    LOADED("LOADED"),
    BATTERY_DEAD("Dead"),
    BATTERY_OVER_VOLTAGE("Over Voltage"),
    BATTERY_OVER_HEAT("Over Heat"),
    BATTERY_FAILURE("Failure"),
    BATTERY_GOOD("Good"),
    UNKNOWN("Unknown"),
    DBM("dBm"),
    S("S"),E("E"),m("m"),ms("ms"),Kbps("Kbps"),KBps("KBps"),
    PERCENT("%"),
    HYPHEN("-"),
    TIMED_OUT("Timed Out"),
    SECONDS("Second(s)"),
    MILISECOND("ms"),
    COLON(":"),
    SLASH("/"),
    QUESTION("?"),
    EQUALS("="),
    LEFT_PARENTHESES("("),
    RIGHT_PARENTHESES(")"),
    SPACE(" "),
    PIXEL("p"),
    COMMA(" ,"),
    COMMANS(","),
    WORKING_HOUR("Service is Running - Working Hour"),
    NON_WORKING_HOUR("Service is Running - Non Working Hour"),
    RETRIEVE_CONF("Retrieving Configuration, Please Wait.."),
    DEVICE_ID("Device Id"),
    IDLE("IDLE"),
    INFINITY("Infinity"),
    IDR("IDR"),
    CARRIAGE_NO("Carriage No :"),
    SEAT_NO("Seat No :"),
    TRAIN_NO("Train No :"),
    CURRENCY_IDR("IDR "),
    TOTAL_ORDER("Total Order :"),
    TOTAL_PAID("Total Paid :"),
    ;


    private String internalValue;
    private ViewConstant(String code){
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
