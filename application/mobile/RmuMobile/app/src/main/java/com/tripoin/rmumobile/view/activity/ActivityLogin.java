package com.tripoin.rmumobile.view.activity;


import com.tripoin.rmumobile.R;
import com.tripoin.rmumobile.security.api.ASecureActivity;

import roboguice.inject.ContentView;

/**
 * Created by Achmad Fauzi on 9/21/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */

@ContentView(R.layout.activity_login)
public class ActivityLogin extends ASecureActivity {


    @Override
    protected int getOptionMenuLayoutId() {
        return R.menu.empty_menu;
    }

    @Override
    protected String initActionBarTitle() {
        return stringEmpty;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void setupValues() {

    }
}
