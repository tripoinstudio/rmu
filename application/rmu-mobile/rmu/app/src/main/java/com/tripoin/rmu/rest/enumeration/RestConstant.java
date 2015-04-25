package com.tripoin.rmu.rest.enumeration;

/**
 * Created by Achmad Fauzi on 12/30/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Strings Constant related to Asynchronous messages
 */
public enum RestConstant {

    WS_CONTEXT("tripoin-core-web-service/wscontext/"),
    //TSK items
    HTTP("http"),
    HTTP_GET("GET"),
    HTTP_POST("POST"),
    TSK_CHECK_PHONE_NUMBER("CHECK_MNUMBER"),
    TSK_LOGIN("login"),
    TSK_SEAT("seat"),
    TSK_CARRIAGE("carriage"),
    TSK_MENU("menu"),
    TSK_ORDER_DETAIL("orderDetail"),
    TSK_ORDER_HEADER("orderHeader"),
    TSK_TRAIN("train"),
    TSK_CHANGE_PASSWORD("CP"),
    TSK_YOUTUBE_SELF_TEST("YST"),
    TSK_GET_ADMIN_CONFIGURATION("GAC"),
    TSK_GET_ADMIN_CONFIGURATION2("GAC2"),
    TSK_LOGOUT("LOGOUT"),
    TSK_ACTIVATE_ACCOUNT("ACTACC"),
    TSK_DCC("DCC"),
    TSK_DCC2("DCC2"),
    TSK_PN("PN"),
    TSK_PN2("PN2"),

    TSK_ST("ST"),
    TSK_ST2("ST2"),
    TSK_ST3("ST3"),
    TSK_STL("STL"),
    TSK_STD("STD"),
    TSK_STL2("STL2"),
    TSK_STD2("STD2"),
    TSK_BST2("BST2"),
    TSK_BST3("BST3"),
    TSK_BST4("BST4"),
    TSK_BST5("BST5"),
    TSK_YST2("YST2"),

    TSK_VIDEO_VIEW("VVST2"),
    TSK_VIDEO_VIEW3("VVST3"),
    TSK_VIDEO_VIEW4("VVST4"),
    TSK_VIDEO_VIEW5("VVST5"),

    //Pattern items
    DEVICE_ID("deviceId"),
    MNUMBER("mnumber"),
    CURRENT_PASSWORD("current_password"),
    PASSWORD("password"),
    NEW_PASSWORD("new_password"),
    RENEW_PASSWORD("renew_password"),
    REPASSWORD("repassword"),
    REAL_NAME("realname"),
    DATE_TIME("datetime"),
    START_TEST("start_test"),
    END_TEST("end_test"),
    TEST_TYPE("test_type"),
    OCT("oct"),
    BST("bst"),
    VST("vst"),
    SST("sst"),
    BATCH_ID_("batch_id_"),
    BATCH_ID("batch_id"),
    VINITBUFF_("vinitbuff_"),
    VREBUFF_("vrebuff_"),
    TBUFFEVT_("tbuffevt_"),
    TBUFFTIME_("tbufftime_"),
    VID_("vid_"),
    VDUR_("vdur_"),
    VURL_("vurl_"),
    VTHROUGHPUT_("vthroughput_"),
    VDEFINITION_("vdefinition_"),
    SIZE("size"),
    VRESOLUTION_("vresolution_"),
    DATETIME_("datetime_"),
    YST2("YST2"),
    CAUSE("cause"),
    RQID("rqid"),
    TSK("tsk"),
    LAT("lat"),
    LONG("long"),
    ACCU("accu"),
    IMEI("imei"),
    IMSI("imsi"),
    PHONE_TYPE("phone_type"),
    OS("os"),
    OPERATOR("operator"),
    SN("sn"),
    MCC("mcc"),
    MNC("mnc"),
    LN("ln"),
    BATT("batt"),
    LOCAL("local"),
    ADDR("addr"),
    RXL("rxl"),
    RXQ("rxq"),
    BER("ber"),
    NETTYPE("nettyp"),
    LAC("lac"),
    CELLID("cellid"),
    CONNSTAT("connstat"),
    SRVCSTAT("srvcstat"),
    IPADDR("ipaddr"),
    WIFI_NAME("wifi_name"),
    USER("user"),
    APN("apn"),
    USER_CODE("user_code"),
    TEST_STATUS("test_status"),
    REASON("reason"),
    FAILED("F"),
    PASS("P"),
    LT_("lt_"),
    LS_("ls_"),
    THP_("thp_"),
    URL_("url_"),
    WEB_SIZE_("web_size_"),
    TEST_ID("test_id"),
    LATENCY("latency"),
    DOWNLOAD("download"),
    LATENCY_UPPERCASE("LATENCY"),
    DOWNLOAD_UPPERCASE("DOWNLOAD"),


    //Rest dialog messages
    CHECKING_PHONE_NUMBER("Checking phone number"),
    AUTHENTICATING("Authenticating"),
    ACTIVATING_ACCOUNT("Activating account"),
    SIGN_OUT("Sign out"),
    PROCESSING_CHANGE_PASSWORD("Processing change password"),
    PROCESSING_GEOCODER("Processing geocoder"),
    TIMED_OUT("Timed Out"),
    RETRIEVE_CONFIGURATION("Retrieving configuration"),
    FETCH_NETWORK_INFO("Fetch network information"),
    SENDING_DOWNLOAD_RESULT("Sending download result"),
    SENDING_LATENCY_RESULT("Sending latency result"),
    SENDING_SPEED_TEST_RESULT("Sending speed test result"),
    SENDING_VIDEO_TEST_RESULT("Sending video test result"),
    MEASURING_LATENCY("Measuring latency"),
    MEASURING_DOWNLOAD("Measuring download"),
    MEASURING_UPLOAD("Measuring upload"),
    SEND_PHONE_NETWORK("Sending Phone Network information"),
    SEND_VIDEO_RESULT("Sending Video Test Result"),
    CURRENT_LOCATION_NULL("CurrentLocation is Null, retry"),
    REVERSE_GEOCODING_NOT_AVAILABLE("Reverse-Geocoding not available"),
    PLEASE_ENABLE_GOOGLE_LOCATION_SERVICE("Please enable Google Location Service!"),
    DONE("Done"),
    /*Key List*/
    YOUTUBE_API_KEY("AIzaSyDiF0IcR8ucQhe-Ot1It-8Tfkl-cUEFh98"),
    GOOGLE_ANDROID_APPS_KEY("AIzaSyCW5zdI5OTA1SO__YLdBl09iYZVZa3EDSw"),
    //GOOGLE_ANDROID_APPS_KEY("hI_vXmJW7ArZfoF0GMt8k3jP-Z4="),
    GOOGLE_BROWSER_APPS_KEY("AIzaSyDiF0IcR8ucQhe-Ot1It-8Tfkl-cUEFh98"),
    YOUTUBE_PARTS("id,snippet,contentDetails"),

    OK("1"),
    NOT_FOUND("0"),
    ERROR("-1"),
    EQUALS("EQUALS"),
    AND("AND"),
    HTTP_TIMEOUT("30"),
    /*=========================*/
    HEADER_ACCEPT("Accept"),
    HEADER_APP_JSON("application/json"),
    HEADER_AUTHORIZATION("Authorization"),
    HEADER_BASIC("Basic ")
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
