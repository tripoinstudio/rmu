package com.tripoin.rmu.view.activity.impl;

import android.content.Intent;

import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.view.activity.ActivityLogin;
import com.tripoin.rmu.view.activity.ActivityMain;
import com.tripoin.rmu.view.activity.api.ILogoutHandler;
import com.tripoin.rmu.view.activity.api.IMainUtilActivity;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 11:20 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class MainUtilImplActivity implements IMainUtilActivity {

    private ActivityMain activityMain;

    public MainUtilImplActivity(ActivityMain activityMain) {
        this.activityMain = activityMain;
    }

    @Override
    public void gotoNextActivity(Class<?> clazz, String extraKey, String extraContent) {
        Intent intent = new Intent( activityMain, clazz );
        intent.putExtra( extraKey, extraContent );
        activityMain.startActivity(intent);
    }

    @Override
    public void gotoNextActivity(Class<?> clazz, String extraKey, Serializable extraContent) {
        Intent intent = new Intent( activityMain, clazz );
        intent.putExtra( extraKey, extraContent );
        activityMain.startActivity(intent);
    }

    public void exitApplication() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activityMain.startActivity(intent);
    }

    @Override
    public void detectLoginStatus(ILogoutHandler iLogoutHandler) {
        if( iLogoutHandler.checkLoginStatus() ){
            gotoNextActivity(ActivityMain.class, PropertyConstant.USER_NAME.toString(), "");
        }else{
            gotoNextActivity(ActivityLogin.class, PropertyConstant.USER_NAME.toString(), "");
        }
    }


}
