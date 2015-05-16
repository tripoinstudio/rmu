package com.tripoin.rmu.view.activity.impl;

import android.util.Log;

import com.tripoin.rmu.R;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.activity.ActivityLogin;
import com.tripoin.rmu.view.activity.ActivityMain;
import com.tripoin.rmu.view.activity.api.ISignHandler;
import com.tripoin.rmu.view.enumeration.ViewConstant;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 11:09 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class FirstSignHandlerImpl implements ISignHandler {

    private PropertyUtil securityUtil;
    private ActivityLogin activityLogin;

    public FirstSignHandlerImpl(PropertyUtil securityUtil, ActivityLogin activityLogin) {
        this.securityUtil = securityUtil;
        this.activityLogin = activityLogin;
    }


    @Override
    public void signOut() {
        securityUtil.saveSingleProperty(PropertyConstant.LOGIN_STATUS_KEY.toString(), PropertyConstant.LOGOUT_STATUS_VALUE.toString());

    }

    @Override
    public void detectLoginStatus() {
        Log.d("FIRST SIGN HANDLER", String.valueOf(checkLoginStatus()));
        if(checkLoginStatus()){
            activityLogin.gotoNextActivity(ActivityMain.class, ViewConstant.EMPTY.toString(), ViewConstant.EMPTY.toString());
        }
    }

    @Override
    public boolean checkLoginStatus() {
        boolean result;
        try {
            result = (!securityUtil.getValuePropertyMap(PropertyConstant.USER_NAME.toString()).equals(ViewConstant.EMPTY.toString())) && !(securityUtil.getValuePropertyMap(PropertyConstant.LOGIN_STATUS_KEY.toString()).equals(PropertyConstant.LOGOUT_STATUS_VALUE.toString()));
        }catch ( Exception e){
            result = false;
        }
        return result;
    }


}
