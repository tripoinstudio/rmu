package com.tripoin.rmu.view.activity.api;

import android.content.Context;

/**
 * Created by Achmad Fauzi on 11/19/2014.
 * achmad.fauzi@sigma.co.id
 *
 * This interface is used as common functions for Activity
 */
public interface IBaseActivity {

    /**
     * This method is used to initiate next Activity from current active Activity
     * @param clazz Class
     * @param extraKey String
     * @param extraContent String
     */
    public void gotoNextActivity(Class<?> clazz, String extraKey, String extraContent);


    /**
     * This method is used to initiate current active activity's widgets
     */
    public void initWidget();

    /**
     * This method is used to initiate current active widgets values
     * + initiate Listener for widget
     */
    public void setupValues();

    /**
     * This method is used to acces main key from current active Context
     * @param extraKey String
     * @param extraContent String
     */
    public void goToMainView(String extraKey, String extraContent);

    /**
     * This method is used to exit Application from active Context
     * @param context Context
     */
    public void exitApplication(Context context);

    /**
     * This method is used t setup ActionBar of current active Activity
     */
    public void setupActionBar();

    /**
     * This method is used to setup TypeFace for widgets within current active Activity
     */
    public void setupTypeFace();
}
