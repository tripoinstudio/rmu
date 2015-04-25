package com.tripoin.rmu.view.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tripoin.rmu.R;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 2:33 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentChangeIPServer extends Fragment {

    public FragmentChangeIPServer newInstance(String text){
        FragmentChangeIPServer mFragment = new FragmentChangeIPServer();
        /*Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);*/
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_change_ip_server, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }
}
