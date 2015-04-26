package com.tripoin.rmu.view.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.Toast;

import com.tripoin.rmu.R;

public class FragmentUserProfile extends Fragment {

//    private ImageView imgUserProfile;

    public FragmentUserProfile newInstance(String text){
        Log.d("MASUK", String.valueOf("MASUK SINI"));

        FragmentUserProfile mFragment = new FragmentUserProfile();
        Log.d("MASUK", String.valueOf("MASUK SINI2"));

        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MASUK", String.valueOf("MASUK SINI3"));

        View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);
        Log.d("MASUK", String.valueOf("MASUK SINI4"));

        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        Log.d("MASUK", String.valueOf("MASUK SINI5"));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("MASUK", String.valueOf("MASUK SINI6"));

        super.onActivityCreated(savedInstanceState);
        Log.d("MASUK", String.valueOf("MASUK SINI7"));

        setHasOptionsMenu(false);
        Log.d("MASUK", String.valueOf("MASUK SINI8"));

    }


}
