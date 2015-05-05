package com.tripoin.rmu.view.activity.impl;

import android.content.Context;

import com.tripoin.rmu.R;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.activity.api.ILogoutHandler;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 11:09 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class LogoutHandlerImpl implements ILogoutHandler {

    private PropertyUtil securityUtil;
    private Context context;

    public LogoutHandlerImpl(PropertyUtil securityUtil, Context context) {
        this.securityUtil = securityUtil;
        this.context = context;
    }

    @Override
    public void logout() {
        securityUtil.saveSingleProperty(PropertyConstant.LOGIN_STATUS_KEY.toString(), PropertyConstant.LOGOUT_STATUS_VALUE.toString());
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
