package com.tripoin.rmu.rest.enumeration;

import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.view.enumeration.ViewConstant;

/**
 * Created by Achmad Fauzi on 12/30/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Strings Constant related to Asynchronous messages
 */
public enum RestConstant {

    WS_CONTEXT("tripoin/wscontext/"),

    /*Task Items*/
    ORDER_HEADER_NO("orderHeaderNo"),
    MENU_CODE("menuCode"),
    TSK_SET_ORDER_STATUS("setOrderHeader"),
    ORDER_NO("orderNo"),
    AND_AMPERS("&"),

    HTTP_REST("http"),
    HTTP_GET("GET"),
    HTTP_POST("POST"),
    STATUS("status"),
    TSK_CHECK_PHONE_NUMBER("CHECK_MNUMBER"),
    TSK_LOGIN("login"),
    TSK_CONNECTION("connection"),
    TSK_SEAT("seat"),
    TSK_CARRIAGE("carriage"),
    TSK_MENU("menu"),
    TSK_IMAGEMENU("image"),
    TSK_VERSION("version"),
    TSK_ORDER_DETAIL("getOrderDetail"),
    TSK_PAYMENT("setOrderDetail"),
    TSK_PAYMENT_PARAM("trx_order_detail"),
    TSK_ORDER_HEADER("getOrderHeader"),
    TSK_TRAIN("train"),
    TSK_CHANGE_PASSWORD("CP"),

    TSK_LOGOUT("LOGOUT"),

    /*Pattern Items*/
    IMAGE("images/"),


    //Rest dialog messages
    CHECKING_PHONE_NUMBER("Checking phone number"),
    AUTHENTICATING("Authenticating"),
    TESTING("Testing connection"),
    SIGN_OUT("Sign out"),
    TIMED_OUT("Timed Out"),
    DONE("Done"),

    /*Key List*/

    OK("1"),
    NOT_FOUND("0"),
    ERROR("-1"),
    EQUALS("EQUALS"),
    AND("AND"),
    HTTP_TIMEOUT("30"),
    /*=========================*/
    HEADER_ACCEPT("Accept"),
    HEADER_APP_JSON("application/json"),
    URL_ENCODER("UTF-8"),
    HEADER_AUTHORIZATION("Authorization"),
    HEADER_BASIC("Basic "),

    BASE_URL(HTTP_REST.toString().
            concat(ViewConstant.COLON.toString().
            concat(ViewConstant.SLASH.toString()).
            concat(ViewConstant.SLASH.toString()).
            concat(PropertyConstant.SERVER_HOST_DEFAULT_VALUE.toString()).
            concat(ViewConstant.COLON.toString()).
            concat(PropertyConstant.SERVER_PORT_DEFAULT_VALUE.toString()).
            concat(ViewConstant.SLASH.toString()).
            concat(RestConstant.WS_CONTEXT.toString())))

    ;


    private String internalValue;

    private RestConstant( String code ){
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
