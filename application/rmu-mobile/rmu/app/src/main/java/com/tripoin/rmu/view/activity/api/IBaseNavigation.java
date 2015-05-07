package com.tripoin.rmu.view.activity.api;

import android.content.Context;

/**
 * Created by Achmad Fauzi on 5/7/2015 : 4:53 PM.
 * mailto : achmad.fauzi@sigma.co.id
 *
 * This base navigation can be used both in Activity and Fragment
 */
public interface IBaseNavigation {

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
}
