package com.tripoin.rmu.view.fragment.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;

import com.tripoin.rmu.R;
import com.tripoin.rmu.view.fragment.base.ABaseNavigationDrawerFragment;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 11:32 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class FragmentUpdateStaticData extends ABaseNavigationDrawerFragment {

    @InjectView(R.id.btPrintSelfTest)
    Button btPrint;

    @InjectView(R.id.btScanDevice)
    Button btScan;

    @InjectView(R.id.btConnectDevice)
    Button btConnectDevice;

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

    @OnClick(R.id.btScanDevice)
    public void scan(){

    }

    @OnClick(R.id.btPrintSelfTest)
    public void printSelfTest(){

    }

    @OnClick(R.id.btConnectDevice)
    public void connectToDevice(){
        Intent serverIntent = null;
        serverIntent = new Intent(getActivity(), DeviceListActivity.class);
        startActivityForResult(serverIntent, IBluetoothConstant.REQUEST_CONNECT_DEVICE_SECURE);
    }

}
