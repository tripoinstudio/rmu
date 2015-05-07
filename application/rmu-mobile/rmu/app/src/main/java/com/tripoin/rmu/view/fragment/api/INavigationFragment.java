package com.tripoin.rmu.view.fragment.api;

import android.support.v4.app.Fragment;

import com.tripoin.rmu.view.activity.api.IBaseNavigation;

/**
 * Created by Achmad Fauzi on 5/7/2015 : 4:55 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface INavigationFragment extends IBaseNavigation{

    public void gotoNextFragment(Fragment fragmentView);

    public void gotoPreviousFragment(Fragment fragmentView);

}
