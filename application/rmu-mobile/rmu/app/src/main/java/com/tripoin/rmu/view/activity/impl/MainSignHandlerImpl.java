package com.tripoin.rmu.view.activity.impl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;
import com.tripoin.rmu.rest.api.ILogoutPost;
import com.tripoin.rmu.rest.impl.LogoutRest;
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
public class MainSignHandlerImpl implements ISignHandler, ILogoutPost {

    private PropertyUtil securityUtil;
    private ActivityMain activityMain;

    public MainSignHandlerImpl(PropertyUtil securityUtil, ActivityMain activityMain) {
        this.securityUtil = securityUtil;
        this.activityMain = activityMain;
    }


    @Override
    public void signOut() {
        LogoutRest logoutRest = new LogoutRest(this) {
            @Override
            public Context getContext() {
                return activityMain;
            }
        };
        logoutRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    @Override
    public void detectLoginStatus() {
        Log.d("MAIN SIGN HANDLER", String.valueOf(checkLoginStatus()));
        if(!checkLoginStatus()){
            Intent intent = new Intent( activityMain, ActivityLogin.class );
            intent.putExtra(ViewConstant.EMPTY.toString(), ViewConstant.EMPTY.toString() );
            activityMain.startActivity(intent);
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


    @Override
    public void onPostLogout(Object objectResult) {
        if(objectResult!=null){
            BaseRESTDTO baseRESTDTO = (BaseRESTDTO) objectResult;
            if(baseRESTDTO.getErr_code().equals(ViewConstant.ZERO.toString())){
                securityUtil.saveSingleProperty(PropertyConstant.USER_NAME.toString(), ViewConstant.EMPTY.toString());
                securityUtil.saveSingleProperty(PropertyConstant.LOGIN_STATUS_KEY.toString(), PropertyConstant.LOGOUT_STATUS_VALUE.toString());
                activityMain.iMainActivityUtil.exitApplication();
            }else{
                Toast.makeText(activityMain, "Could not sign out caused by ".concat(baseRESTDTO.getErr_msg()), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(activityMain, "Could not sign out caused by connection problem", Toast.LENGTH_SHORT).show();
        }
    }
}
