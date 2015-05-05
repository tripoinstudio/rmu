package com.tripoin.rmu.view.activity.api;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 11:21 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public interface IMainUtilActivity extends INavigationActivity {

    public void exitApplication();

    public void detectLoginStatus(ILogoutHandler iLogoutHandler);

}
