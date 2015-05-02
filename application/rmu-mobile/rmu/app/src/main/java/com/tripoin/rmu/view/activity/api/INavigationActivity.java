package com.tripoin.rmu.view.activity.api;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 12:48 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface INavigationActivity {

    public void gotoNextActivity(Class<?> clazz, String extraKey, String extraContent ) ;

    public void gotoNextActivity(Class<?> clazz, String extraKey, Serializable extraContent );

    public void exitApplication();
}
