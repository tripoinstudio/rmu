package com.tripoin.rmumobile.view.activity;


import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.BaseDrawerItem;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.tripoin.rmumobile.R;
import com.tripoin.rmumobile.security.api.ASecureActivity;

import roboguice.inject.ContentView;

/**
 * Created by Achmad Fauzi on 11/20/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 */
@ContentView(R.layout.activity_main)
public class ActivityMain extends ASecureActivity {

    @Override
    protected int getOptionMenuLayoutId() {
        return R.menu.empty_menu;
    }

    @Override
    protected String initActionBarTitle() {
        return appName;
    }

    @Override
    public void initWidget() {
        new Drawer().withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.string_drawer_item_home),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.string_drawer_item_settings)
                )
                .build();
    }

    @Override
    public void setupValues() {

    }
}
