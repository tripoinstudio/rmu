package com.tripoin.rmu.view.activity.impl;

import com.tripoin.rmu.R;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.activity.ActivityLogin;
import com.tripoin.rmu.view.activity.ActivityMain;
import com.tripoin.rmu.view.activity.api.ISignHandler;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 11:09 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class SignHandlerImpl implements ISignHandler {

    private PropertyUtil securityUtil;
    private ActivityLogin activityLogin;

    public SignHandlerImpl(PropertyUtil securityUtil, ActivityLogin activityLogin) {
        this.securityUtil = securityUtil;
        this.activityLogin = activityLogin;
    }

    @Override
    public void signOut() {
        securityUtil.saveSingleProperty(PropertyConstant.LOGIN_STATUS_KEY.toString(), PropertyConstant.LOGOUT_STATUS_VALUE.toString());

    }

    @Override
    public void detectLoginStatus() {
        if(checkLoginStatus()){
            activityLogin.gotoNextActivity(ActivityMain.class, "", "");
        }
    }

    @Override
    public boolean checkLoginStatus() {
        boolean result;
        try {
            result = (!securityUtil.getValuePropertyMap(PropertyConstant.USER_NAME.toString()).equals(R.string.string_empty)) && !(securityUtil.getValuePropertyMap(PropertyConstant.LOGIN_STATUS_KEY.toString()).equals(R.string.string_empty));
        }catch ( Exception e){
            result = false;
        }
        return result;
    }


}
