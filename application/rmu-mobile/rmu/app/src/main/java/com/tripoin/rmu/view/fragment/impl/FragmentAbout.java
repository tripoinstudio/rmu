package com.tripoin.rmu.view.fragment.impl;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripoin.rmu.R;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 11:33 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentAbout extends Fragment {

    public FragmentAbout newInstance(String text){
        FragmentAbout mFragment = new FragmentAbout();
        /*Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);*/
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        TextView label1=(TextView)rootView.findViewById(R.id.label_about1);
        Typeface faces1=Typeface.createFromAsset(label1.getResources().getAssets(),"font/Roboto-Light.ttf");
        label1.setTypeface(faces1);
        TextView label2=(TextView)rootView.findViewById(R.id.label_about1);
        Typeface faces2=Typeface.createFromAsset(label2.getResources().getAssets(),"font/Roboto-Light.ttf");
        label2.setTypeface(faces2);
        TextView label3=(TextView)rootView.findViewById(R.id.label_about1);
        Typeface faces3=Typeface.createFromAsset(label3.getResources().getAssets(),"font/Roboto-Light.ttf");
        label3.setTypeface(faces3);
        TextView label4=(TextView)rootView.findViewById(R.id.label_about1);
        Typeface faces4=Typeface.createFromAsset(label4.getResources().getAssets(),"font/Roboto-Light.ttf");
        label4.setTypeface(faces4);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }
}
