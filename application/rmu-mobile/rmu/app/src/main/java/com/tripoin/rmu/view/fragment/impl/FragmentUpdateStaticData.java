package com.tripoin.rmu.view.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tripoin.rmu.R;
import com.tripoin.rmu.view.fragment.base.ABaseNavigationDrawerFragment;


/**
 * Created by Achmad Fauzi on 4/18/2015 : 11:32 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class FragmentUpdateStaticData extends ABaseNavigationDrawerFragment {


    public FragmentUpdateStaticData newInstance(String text){
        FragmentUpdateStaticData mFragment = new FragmentUpdateStaticData();
        return mFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public String getFragmentTitle() {
        return "Update Static Data";
    }

    @Override
    public void initWidget() {

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_update_static_data;
    }

}
