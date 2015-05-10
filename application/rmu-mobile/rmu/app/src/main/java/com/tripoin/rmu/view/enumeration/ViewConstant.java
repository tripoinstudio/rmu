package com.tripoin.rmu.view.enumeration;

/**
 * Created by Achmad Fauzi on 12/30/2014.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 *
 * All Strings for UI message as Constant
 */
public enum ViewConstant {

    FONT_ROBOT_BLACK("font/Roboto-Black.ttf"),
    FONT_ROBOT_BLACK_ITALIC("font/Roboto-BlackItalic.ttf"),
    FONT_ROBOT_BOLD("font/Roboto-Bold.ttf"),
    FONT_ROBOT_ITALIC("font/Roboto-Italic.ttf"),
    FONT_ROBOT_BOLD_ITALIC("font/Roboto-BoldItalic.ttf"),
    FONT_ROBOT_LIGHT("font/Roboto-Light.ttf"),
    FONT_ROBOT_LIGHT_ITALIC("font/Roboto-LightItalic.ttf"),
    FONT_ROBOT_MEDIUM("font/Roboto-Medium.ttf"),
    FONT_ROBOT_MEDIUM_ITALIC("font/Roboto-MediumItalic.ttf"),
    FONT_ROBOT_REGULAR("font/Roboto-Regular.ttf"),
    FONT_ROBOT_THIN("font/Roboto-Thin.ttf"),
    FONT_ROBOT_THIN_ITALIC("font/Roboto-ThinItalic.ttf"),
    FONT_ADAM("font/ADAM.CG PRO.otf"),
    FONT_OPEN_SANS_LIGHT("font/OpenSans-Light.ttf"),

    DEFAULT_ACTION_BAR_TITLE("eRestorasi"),

    /*Fragment Titles*/
    FRAGMENT_ABOUT_TITLE("About Application"),
    FRAGMENT_ORDER_LIST_TITLE("Order List"),
    FRAGMENT_ORDER_DETAIL_TITLE("Order Detail"),
    FRAGMENT_SERVER_CONFIGURATION("Server Configuration"),
    FRAGMENT_BLUETOOTH_CONFIGURATION("Bluetooth Configuration"),

    EMPTY(""),
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
    AT("@"),
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
    /*WORKING_HOUR("Service is Running - Working Hour"),
    NON_WORKING_HOUR("Service is Running - Non Working Hour"),*/
    SERVICE_RUNNING("Service is Running"),
    NEW_ORDER("New Order - "),
    RETRIEVE_CONF("Retrieving Configuration, Please Wait.."),
    DEVICE_ID("Device Id"),
    IDLE("IDLE"),
    INFINITY("Infinity"),
    IDR("IDR"),
    CARRIAGE_NO("Carriage No :"),
    SEAT_NO("Seat No :"),
    TRAIN_NO("Train No :"),
    CURRENCY_IDR("IDR "),
    STOCK("stock "),
    TOTAL_ORDER("Total Order :"),
    TOTAL_PAID("Total Paid : "),
    CURRENCY_PATTERN(",-"),
    PRINT_DASH("\n--------------------------------\n"),
    PRINT_HEADER("\n\n\nPT. Reska Multi Usaha\n"
            .concat("eRestorasi version 1.0\n")
            .concat("Jln. Kapt Subidjanto\n")
            .concat("Telp : 0212345678\n\n")
            .concat(PRINT_DASH.toString())),
    PRINT_FOOTER(PRINT_DASH.toString()
            .concat("Thank You For Order\n")
            .concat("Terima Kasih\n\n\n")),
    RESTART_SERVICE("restart_service"),
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
