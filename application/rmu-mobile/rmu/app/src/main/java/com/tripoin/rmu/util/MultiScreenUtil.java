package com.tripoin.rmu.util;

import android.support.v4.app.FragmentActivity;
import android.view.Display;

/**
 * Created by Ginanjar Aji Sanjaya on 6/9/2015.
 */
public class MultiScreenUtil {
    Display mDisplay;
    public MultiScreenUtil(FragmentActivity fragmentActivity){
        Display mDisplay = fragmentActivity.getWindowManager().getDefaultDisplay();
    }

    public int getWidthScreen(){
        return mDisplay.getWidth();
    }

    public int getHeightScreen(){
        return mDisplay.getHeight();
    }
}
