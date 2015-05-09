package com.tripoin.rmu.util.enumeration;

import android.os.Environment;

import com.tripoin.rmu.util.impl.PropertyUtil;

/**
 * Created by Achmad Fauzi on 12/30/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */
public enum PropertyConstant {

    COMPANY_NAME( "RMU" ),
    PROPERTIES_PATH( Environment.getExternalStorageDirectory().getPath().concat( "/RMU/" ).concat( COMPANY_NAME.toString().concat("/") ) ),
    SERVER_HOST_KEY("SERVER_HOST"),

    APP_MODE("development"),
    DEVELOPMENT("development"),
    PRODUCTION("production"),

    //dev
    SERVER_HOST_DEFAULT_VALUE("192.168.1.109"),
    /*SERVER_HOST_DEFAULT_VALUE("192.168.43.23"),*/
    SERVER_PORT_DEFAULT_VALUE("8080"),

    //prod
    /*SERVER_HOST_DEFAULT_VALUE("tripoin-rdlfdl.rhcloud.com"),
    SERVER_PORT_DEFAULT_VALUE("443"),*/


    CHIPPER_AUTH("CHIPPER_AUTH"),
    SERVER_PORT_KEY("SERVER_PORT") ,
    USER_NAME("user_name"),
    USER_DTO("user_dto"),
    MNUMBER("mnumber"),
    PASSWORD_EXPIRED_DATE("password_expired_date"),
    LOGIN_STATUS_KEY("status"),
    LOGIN_STATUS_VALUE("logged_in"),
    LOGOUT_STATUS_VALUE("logged_in"),
    PROPERTY_FILE_NAME("rmu.properties"),
    LOGIN_FILE_NAME("rmu.login"),
    UNKNOWN("Unknown"),
    DEFAULT_WS_PATH("/mforce-rest/ws/service/"),
    START_WORKING_HOUR_KEY("START_WORKING_HOUR"),
    START_WORKING_HOUR_DEFAULT_VALUE("7"),
    STOP_WORKING_HOUR_KEY("STOP_WORKING_HOUR"),
    STOP_WORKING_HOUR_DEFAULT_VALUE("16"),
    VIDEO_RESOLUTION_KEY("VIDEO_RESOLUTION"),
    VIDEO_RESOLUTION_DEFAULT_VALUE("240p,720p,1080p"),
    LATENCY_URL_KEY("LATENCY_URL"),
    LATENCY_URL_DEFAULT_VALUE(""),
    DOWNLOAD_URL_KEY("DOWNLOAD_URL"),
    DOWNLOAD_URL_DEFAULT_VALUE("http://central.maven.org/maven2/org/codehaus/jackson/jackson-mapper-asl/1.9.13/jackson-mapper-asl-1.9.13.jar"),
    UPLOAD_URL_KEY( "UPLOAD_URL" ),
    UPLOAD_URL_DEFAULT_VALUE(""),
    FILE_UPLOAD_KEY( "FILE_UPLOAD" ),
    FILE_UPLOAD_DEFAULT_VALUE( PropertyConstant.PROPERTIES_PATH.toString()+PropertyConstant.PROPERTY_FILE_NAME.toString() ),
    VALID_AGE( "17" ),
    VIDEO_VIEW_KEY("VIDEO_VIEW_KEY"),
    VIDEO_VIEW_DEFAULT_VALUE("http://techslides.com/demos/sample-videos/small.mp4");


    //https://dl.dropboxusercontent.com/s/5kis7t5rtue0jzf/telkomsel480p.mp4,https://dl.dropboxusercontent.com/s/u4phquygus9zqh5/telkomsel720p.mp4
    //http://techslides.com/demos/sample-videos/small.mp4
//    VIDEO_VIEW_DEFAULT_VALUE("http://techslides.com/demos/sample-videos/small.mp4,http://techslides.com/demos/sample-videos/small.mp4");

//    "http://techslides.com/demos/sample-videos/small.mp4,

    private String internalValue;


    private PropertyConstant( String code ){
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
