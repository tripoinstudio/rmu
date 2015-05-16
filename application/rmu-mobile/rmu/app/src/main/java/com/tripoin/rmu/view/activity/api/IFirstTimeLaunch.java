package com.tripoin.rmu.view.activity.api;

/**
 * Created by Achmad Fauzi on 5/16/2015 : 12:11 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 *
 * Detect whether this app is in first time launch or not
 */
public interface IFirstTimeLaunch {

    /**
     * check whether the application is in first time launch or not
     * @return boolean
     */
    public boolean isNotFirstTimeLaunch();

    /**
     * check whether the application is in first time launch or not
     * false if it's first time launch
     * true if not
     * @return boolean
     */
    public boolean isNotFirstTimeLaunchWithoutSignOut();

    public void setFirstTimeLaunchStatus( boolean status );

}
