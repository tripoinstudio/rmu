package com.tripoin.rmu.view.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import com.tripoin.rmu.view.activity.api.IBaseActivity;

import java.io.Serializable;

import br.liveo.navigationliveo.NavigationLiveo;
import roboguice.activity.RoboActionBarActivity;

/**
 * Created by Achmad Fauzi on 4/26/2015 : 11:33 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class ACustomNavigationLiveoActivity extends NavigationLiveo implements IBaseActivity{

    protected RoboActionBarActivity roboActionBarActivity = new RoboActionBarActivity();

    @Override
    public void onUserInformation() {

    }

    @Override
    public void onInt(Bundle bundle) {

    }

    @Override
    public void gotoNextActivity(Class<?> clazz, String extraKey, String extraContent) {

    }

    @Override
    public void gotoNextActivity(Class<?> clazz, String extraKey, Serializable extraContent) {

    }

    @Override
    public void gotoNextActivity(Class<?> clazz, String extraKey, Parcelable extraContent) {

    }

    @Override
    public void initWidget() {

    }

    @Override
    public void setupValues() {

    }

    @Override
    public void goToMainView(String extraKey, String extraContent) {

    }

    @Override
    public void exitApplication(Context context) {

    }

    @Override
    public void setupActionBar() {

    }

    @Override
    public void setupTypeFace() {

    }
}
