package com.tripoin.rmu.view.ui;

/**
 * Created by HP M4 on 5/7/2015.
 */
public class PaddingHelper {

    public String leftPaddingString(String data, Integer paddingCount, String charPadding){
        return String.format("%"+paddingCount+"s",data).replace(" ", charPadding);
    }

    public String rightPaddingString(String data, Integer paddingCount, String charPadding){
        return String.format("%-"+paddingCount+"s",data).replace(" ", charPadding);
    }

}
