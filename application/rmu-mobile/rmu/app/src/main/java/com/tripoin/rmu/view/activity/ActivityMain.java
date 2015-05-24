package com.tripoin.rmu.view.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.scheduler.listener.SchedulerServiceListener;
import com.tripoin.rmu.feature.scheduler.trigger.AlarmManagerStarter;
import com.tripoin.rmu.feature.version.AppUpdate;
import com.tripoin.rmu.feature.version.UpdateApp;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.util.NetworkConnectivity;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.activity.api.IFirstTimeLaunch;
import com.tripoin.rmu.view.activity.api.ISignHandler;
import com.tripoin.rmu.view.activity.api.IMainUtilActivity;
import com.tripoin.rmu.view.activity.impl.FirstTimeLaunchImpl;
import com.tripoin.rmu.view.activity.impl.MainSignHandlerImpl;
import com.tripoin.rmu.view.activity.impl.MainUtilImplActivity;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.impl.FragmentAbout;
import com.tripoin.rmu.view.fragment.impl.FragmentAddOrder;
import com.tripoin.rmu.view.fragment.impl.FragmentChangeBluetooth;
import com.tripoin.rmu.view.fragment.impl.FragmentChangeIPServer;
import com.tripoin.rmu.view.fragment.impl.FragmentMenuList;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderList;
import com.tripoin.rmu.view.fragment.impl.FragmentUserProfile;

import java.util.ArrayList;
import java.util.List;

import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;

/**
 * Created by Achmad Fauzi on 11/20/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Navigation Liveo for Drawer Menu
 */
public class ActivityMain extends NavigationLiveo implements NavigationLiveoListener {

    private List<String> mListNameItem;
    int layoutContainerIdGlobal = 0;

    private PropertyUtil securityUtil;
    private PropertyUtil propertyUtil;
    public IMainUtilActivity iMainActivityUtil;
    private ISignHandler iSignHandler;
    private IFirstTimeLaunch iFirstTimeLaunch;
    private NetworkConnectivity networkConnectivity;


    @Override
    public void onUserInformation() {
        propertyUtil = new PropertyUtil(PropertyConstant.PROPERTY_FILE_NAME.toString(), this);
        String userName = propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_NAME.toString());
        if(userName != null){
            this.mUserName.setText(userName);
        }else{
            this.mUserName.setText(ViewConstant.EMPTY.toString());
        }

        String userEmail = propertyUtil.getValuePropertyMap(PropertyConstant.WAITRESS_EMAIL.toString());
        if(userEmail != null){
            this.mUserEmail.setText(userEmail);
        }else{
            this.mUserEmail.setText(ViewConstant.EMPTY.toString());
        }
        this.mUserName.setTextColor(getResources().getColor(R.color.black_light));
        this.mUserEmail.setTextColor(getResources().getColor(R.color.black_light));
        Picasso.with(this).load(R.drawable.foto).into(this.mUserPhoto);
        this.mUserBackground.setImageResource(R.drawable.wavy_green_background4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(networkConnectivity.checkConnectivity()){
            if(iSignHandler.checkLoginStatus()){
                if(iFirstTimeLaunch.isNotFirstTimeLaunch()){
                    iSignHandler.detectLoginStatus();
                }
            }else{
                iMainActivityUtil.gotoNextActivity(ActivityLogin.class, ViewConstant.EMPTY.toString(), ViewConstant.EMPTY.toString());
            }
        }else{
            if( !iSignHandler.checkLoginStatus() ){
                iFirstTimeLaunch.setFirstTimeLaunchStatus( false );
            }else{
                iFirstTimeLaunch.setFirstTimeLaunchStatus( true );
            }
        }
    }


    @Override
    public void onInt(Bundle bundle) {
        this.setNavigationListener(this);
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), this);
        iMainActivityUtil = new MainUtilImplActivity(this);
        iSignHandler = new MainSignHandlerImpl(securityUtil, this);
        iFirstTimeLaunch = new FirstTimeLaunchImpl(this, iSignHandler, securityUtil);
        networkConnectivity = new NetworkConnectivity(this);

        // name of the list items
        mListNameItem = new ArrayList<>();
        mListNameItem.add(0, getString(R.string.add_order));
        mListNameItem.add(1, getString(R.string.menu_list));
        mListNameItem.add(2, getString(R.string.order_list));
        mListNameItem.add(3, getString(R.string.about));
        mListNameItem.add(4, getString(R.string.more_markers));
        mListNameItem.add(5, getString(R.string.change_ip));
        mListNameItem.add(6, getString(R.string.change_bluetooth));

        // icons list items
        List<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, R.drawable.ic_add_to_photos_black_24dp);
        mListIconItem.add(1, R.drawable.ic_format_list_bulleted_black_24dp);
        mListIconItem.add(2, R.drawable.ic_format_list_bulleted_black_24dp);
        mListIconItem.add(3, R.drawable.ic_person_black_24dp);
        mListIconItem.add(4, 0);
        mListIconItem.add(5, R.drawable.ic_satellite_black_24dp);
        mListIconItem.add(6, R.drawable.ic_bluetooth_audio_black_24dp);


        List<Integer> mListHeaderItem = new ArrayList<>();
        mListHeaderItem.add(4);

        setDefaultStartPositionNavigation(2);

        SparseIntArray mSparseCounterItem = new SparseIntArray();
        mSparseCounterItem.put(1, 0);
        mSparseCounterItem.put(2, 0);

        //If not please use the FooterDrawer use the setFooterVisible(boolean visible) method with value false
        this.setFooterInformationDrawer(R.string.string_log_out, R.drawable.ic_settings_black_24dp);

        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, mSparseCounterItem);

        Intent schedulerIntent = new Intent(ActivityMain.this, SchedulerServiceListener.class);
        PendingIntent pendingIntent = PendingIntent.getService(ActivityMain.this, 0, schedulerIntent, 0);
        AlarmManagerStarter alarmManagerStarter = new AlarmManagerStarter( this, pendingIntent );
        alarmManagerStarter.startAlarmManager();

        Intent intent = getIntent();
        String schedulerAction = intent.getAction();
        if( schedulerAction != null){
            if( schedulerAction.equals("UPDATE")){
                new ApplicationDownloaderAsync().execute("update.apk");
            }else{
                FragmentManager mFragmentManager = getSupportFragmentManager();
                FragmentOrderList fragmentOrderList = new FragmentOrderList().newInstance(schedulerAction);
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderList).commit();
            }
        }
    }

    private class ApplicationDownloaderAsync<String, Void> extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            AppUpdate appUpdate = new AppUpdate("http://tripoin-rdlfdl.rhcloud.com/tripoin/wscontext/apk/rmu-dev%20V1.0.apk", PropertyConstant.PROPERTIES_PATH.toString().concat(params[0].toString()));
            appUpdate.setContext(ActivityMain.this);
            appUpdate.downloadImage();
            return null;
        }
    }

    @Override
    public void onItemClickNavigation(int position, int layoutContainerId) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        String listName = mListNameItem.get(position);
        FragmentMenuList fragmentMenuList = null;
        FragmentAddOrder fragmentAddOrder = null;
        FragmentAbout fragmentAbout = null;
        FragmentOrderList fragmentOrderList = null;
        FragmentChangeIPServer fragmentChangeIPServer = null;
        FragmentChangeBluetooth fragmentChangeBluetooth = null;
        switch (position){
            case 0:
                fragmentAddOrder = new FragmentAddOrder().newInstance();
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentAddOrder).addToBackStack(null).commit();
                break;
            case 1 :
                fragmentMenuList = new FragmentMenuList().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentMenuList).addToBackStack(null).commit();
                break;
            case 2 :
                layoutContainerIdGlobal = layoutContainerId;
                fragmentOrderList = new FragmentOrderList().newInstance("");
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentOrderList).commit();
                break;
            case 3 :
                fragmentAbout = new FragmentAbout().newInstance();
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentAbout).addToBackStack(null).commit();
                break;
            case 5 :
                fragmentChangeIPServer = new FragmentChangeIPServer().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentChangeIPServer).addToBackStack(null).commit();
                break;
            case 6:
                fragmentChangeBluetooth = new FragmentChangeBluetooth().newInstance();
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentChangeBluetooth).addToBackStack(null).commit();
                break;
            default:
        }
    }

    @Override
    public void onPrepareOptionsMenuNavigation(Menu menu, int position, boolean visible) {
        switch (position) {
            case 0:
                /*menu.findItem(R.id.menu_add).setVisible(!visible);
                menu.findItem(R.id.menu_search).setVisible(!visible);*/
                break;
            case 1:
                /*menu.findItem(R.id.menu_add).setVisible(!visible);
                menu.findItem(R.id.menu_search).setVisible(!visible);*/
                break;
        }
    }

    @Override
    public void onClickFooterItemNavigation(View view) {
        iSignHandler.signOut();
        iMainActivityUtil.exitApplication();
    }

    @Override
    public void onClickUserPhotoNavigation(View view) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentUserProfile fragmentUserProfile = null;
        fragmentUserProfile = new FragmentUserProfile().newInstance("User Profile");
        mFragmentManager.beginTransaction().replace(layoutContainerIdGlobal, fragmentUserProfile).commit();
    }

}
