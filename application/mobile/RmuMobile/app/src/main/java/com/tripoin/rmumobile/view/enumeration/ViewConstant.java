package com.tripoin.rmumobile.view.enumeration;

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

    ACTION_BAR_TITLE("Arium MForce MQA"),

    AD_STARTED("AD Started"),
    ERROR("ERROR"),
    ZERO("0"),
    ONE("1"),
    SUCCESS("SUCCESS"),
    LOADED("LOADED"),
    VIDEO("VIDEO"),
    VIDEO_LOADING("VIDEO LOADING"),
    VIDEO_LOADED("VIDEO LOADED"),
    VIDEO_ENDED("VIDEO ENDED"),
    VIDEO_STARTED("VIDEO STARTED"),
    ON_BUFFER("ON BUFFER"),
    BUFFERING("BUFFERING"),
    BUFFERING_("Buffering..."),
    PAUSED("PAUSED"),
    ON_PLAY("ON PLAY"),
    ON_LOAD("ON LOAD"),
    PLAYING("PLAYING"),
    SEEKING_TO("SEEKING TO"),
    ON_STOP("ON STOP"),
    STOPPED("STOPPED"),
    VIDEO_BUNDLE_LIST("video_bundle_list"),
    BROWSER_BUNDLE_LIST("browser_bundle_list"),
    VIDEO_URL_BUNDLE_LIST("video_url_bundle_list"),
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
    RESTART_SERVICE("restart_service"),
    LATENCY("Latency"),
    DOWNLOAD("Download"),
    UPLOAD("Upload"),
    URL_VIDEO_TO_MEASURE("https://www.youtube.com/watch?v="),
    SECONDS("Second(s)"),
    MILISECOND("ms"),
    COLON(":"),
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
    INFINITY("Infinity");

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
