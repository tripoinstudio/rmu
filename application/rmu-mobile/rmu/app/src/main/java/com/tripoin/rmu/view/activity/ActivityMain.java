package com.tripoin.rmu.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.view.fragment.impl.FragmentAbout;
import com.tripoin.rmu.view.fragment.impl.FragmentAddOrder;
import com.tripoin.rmu.view.fragment.impl.FragmentChangeBluetooth;
import com.tripoin.rmu.view.fragment.impl.FragmentChangeIPServer;
import com.tripoin.rmu.view.fragment.impl.FragmentMenuList;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderList;
import com.tripoin.rmu.view.fragment.impl.FragmentUpdateStaticData;
import com.tripoin.rmu.view.fragment.impl.FragmentUserProfile;

import java.util.ArrayList;
import java.util.List;

import br.liveo.interfaces.NavigationLiveoListener;
import br.liveo.navigationliveo.NavigationLiveo;

/**
 * Created by Achmad Fauzi on 11/20/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 */
public class ActivityMain extends NavigationLiveo implements NavigationLiveoListener {

    private List<String> mListNameItem;

    @Override
    public void onUserInformation() {
        this.mUserName.setText("Bangkit Pratolo");
        this.mUserEmail.setText("bangkit@gmail.com");
        this.mUserPhoto.setImageResource(R.drawable.ic_launcher);
        this.mUserBackground.setImageResource(R.drawable.ic_user_background);
//      /*  View mCustomHeader = getLayoutInflater().inflate(R.layout.fragment_user_profile, this.getListView(), false);
//        ImageView imageView = (ImageView) mCustomHeader.findViewById(R.id.imgUserProfile);
//        this.addCustomHeader(mCustomHeader);*/
    }

    @Override
    public void onInt(Bundle bundle) {

        this.setNavigationListener(this);

        // name of the list items
        mListNameItem = new ArrayList<>();
        mListNameItem.add(0, getString(R.string.add_order));
        mListNameItem.add(1, getString(R.string.menu_list));
        mListNameItem.add(2, getString(R.string.order_list));
        mListNameItem.add(3, getString(R.string.update_static_order));
        mListNameItem.add(4, getString(R.string.about));
        mListNameItem.add(5, getString(R.string.more_markers));
        mListNameItem.add(6, getString(R.string.change_ip));
        mListNameItem.add(7, getString(R.string.change_bluetooth));
        mListNameItem.add(8, getString(R.string.more_markers));
        mListNameItem.add(9, getString(R.string.user_profile));

        // icons list items
        List<Integer> mListIconItem = new ArrayList<>();
        mListIconItem.add(0, R.drawable.ic_add_to_photos_black_24dp);
        mListIconItem.add(1, R.drawable.ic_format_list_bulleted_black_24dp);
        mListIconItem.add(2, R.drawable.ic_format_list_bulleted_black_24dp);
        mListIconItem.add(3, R.drawable.ic_edit_black_24dp);
        mListIconItem.add(4, R.drawable.ic_person_black_24dp);
        mListIconItem.add(5, 0);
        mListIconItem.add(6, R.drawable.ic_satellite_black_24dp);
        mListIconItem.add(7, R.drawable.ic_bluetooth_audio_black_24dp);
        mListIconItem.add(8, 0);
        mListIconItem.add(9, R.drawable.ic_person_grey600_24dp);

        List<Integer> mListHeaderItem = new ArrayList<>();
        mListHeaderItem.add(5);

        SparseIntArray mSparseCounterItem = new SparseIntArray();
        mSparseCounterItem.put(1, 20);
        mSparseCounterItem.put(2, 6);

        //If not please use the FooterDrawer use the setFooterVisible(boolean visible) method with value false
        this.setFooterInformationDrawer(R.string.settings, R.drawable.ic_settings_black_24dp);

        this.setNavigationAdapter(mListNameItem, mListIconItem, mListHeaderItem, mSparseCounterItem);

    }

    @Override
    public void onItemClickNavigation(int position, int layoutContainerId) {
        Log.d("LAYOUTCONTAINERID", String.valueOf(layoutContainerId));
        Log.d("POSITIONCONTAINER", String.valueOf(position));

        FragmentManager mFragmentManager = getSupportFragmentManager();
        String listName = mListNameItem.get(position);
        FragmentMenuList fragmentMenuList = null;
        FragmentAddOrder fragmentAddOrder = null;
        FragmentAbout fragmentAbout = null;
        FragmentOrderList fragmentOrderList = null;
        FragmentChangeIPServer fragmentChangeIPServer = null;
        FragmentChangeBluetooth fragmentChangeBluetooth = null;
        FragmentUpdateStaticData fragmentUpdateStaticData = null;
        FragmentUserProfile fragmentUserProfile = null;
        switch (position){
            case 0:
                fragmentAddOrder = new FragmentAddOrder().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentAddOrder).commit();
                break;
            case 1 :
                fragmentMenuList = new FragmentMenuList().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentMenuList).commit();
                break;
            case 2 :
                fragmentOrderList = new FragmentOrderList().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentOrderList).commit();
                break;
            case 3 :
                fragmentUpdateStaticData = new FragmentUpdateStaticData().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentUpdateStaticData).commit();
                break;
            case 4 :
                fragmentAbout = new FragmentAbout().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentAbout).commit();
                break;
            case 6 :
                fragmentChangeIPServer = new FragmentChangeIPServer().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentChangeIPServer).commit();
                break;
            case 7:
                fragmentChangeBluetooth = new FragmentChangeBluetooth().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentChangeBluetooth).commit();
                break;
            case 9:
                fragmentUserProfile = new FragmentUserProfile().newInstance(listName);
                mFragmentManager.beginTransaction().replace(layoutContainerId, fragmentUserProfile).commit();
                break;
            default:;
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
                menu.findItem(R.id.menu_add).setVisible(!visible);
                menu.findItem(R.id.menu_search).setVisible(!visible);
                break;
        }
    }


    @Override
    public void onClickFooterItemNavigation(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    @Override
    public void onClickUserPhotoNavigation(View view) {
//        setContentView(R.layout.fragment_user_profile);
////        super.onClickUserPhotoNavigation
////        Log.d("VIEWUSERCLIKID", String.valueOf(view.getId()));
//        FragmentManager mFragmentManager = getSupportFragmentManager();
//        FragmentUserProfile fragmentUserProfile = new FragmentUserProfile().newInstance("User Profile");
//        mFragmentManager.beginTransaction().add(view.getId(), fragmentUserProfile).commit();
        Intent intent = new Intent(this, ActivityMain.class);
        intent.putExtra( "user_name", "userName");
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ActivityMain.class);
        startActivity(intent);
//        FragmentManager mFragmentManager = getSupportFragmentManager();
//        mFragmentManager.beginTransaction().replace(super.getListView().getId(), new FragmentMenuList().newInstance(getString(R.string.menu_list))).commit();
//        setContentView(R.layout.abc_action_menu_layout);
    }
}