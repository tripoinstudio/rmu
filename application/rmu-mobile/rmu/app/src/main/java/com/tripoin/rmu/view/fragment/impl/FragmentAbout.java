package com.tripoin.rmu.view.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.bluetooth.BluetoothEngine;
import com.tripoin.rmu.feature.bluetooth.listener.BluetoothReceiver;
import com.tripoin.rmu.model.DTO.print_message.PrintMessageDTO;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.base.ABaseNavigationDrawerFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 11:33 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class FragmentAbout extends ABaseNavigationDrawerFragment {

    @InjectView(R.id.label_about1) TextView lblAbout1;
    @InjectView(R.id.label_about2) TextView lblAbout2;
    @InjectView(R.id.label_about3) TextView lblAbout3;


    public FragmentAbout newInstance(){
        return new FragmentAbout();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void initWidget() {
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public List<TextView> getTextViews() {
        textViews = new ArrayList<TextView>();
        textViews.add(lblAbout1);
        textViews.add(lblAbout2);
        textViews.add(lblAbout3);
        return textViews;
    }

    @Override
    public String getFragmentTitle() {
        return ViewConstant.FRAGMENT_ABOUT_TITLE.toString();
    }


}
