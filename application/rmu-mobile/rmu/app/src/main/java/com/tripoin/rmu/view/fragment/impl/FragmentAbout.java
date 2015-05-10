package com.tripoin.rmu.view.fragment.impl;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;
import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.bluetooth.BluetoothEngine;
import com.tripoin.rmu.feature.bluetooth.listener.BluetoothReceiver;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.base.ABaseNavigationDrawerFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 11:33 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class FragmentAbout extends ABaseNavigationDrawerFragment{

    @InjectView(R.id.label_about1) TextView lblAbout1;
    @InjectView(R.id.label_about2) TextView lblAbout2;
    @InjectView(R.id.label_about3) TextView lblAbout3;
    BluetoothEngine bluetoothEngine;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBluetoothDevice;
    private BluetoothReceiver mbluetoothReceiver;

    private String data1;

    public FragmentAbout newInstance(){
        FragmentAbout mFragment = new FragmentAbout();
        return mFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String SelectedBDAddress = "";
        if((mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter())==null)
        {
            Log.d("EMPTY","Did not find the Bluetooth adapter");
        }
        if(!mBluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 2);
        }

        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Log.d("BLUETOOTH ENGINE ADAPTER", "NULL");
            }
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                getActivity().startActivityForResult(enableBluetooth, 0);
            }
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    if (device.getName().trim().equalsIgnoreCase("RPP-02")) {
                        mBluetoothDevice = device;
                        Log.d("test","Bluetooth Device Found");
                        break;
                    }
                    Log.d("test2", device.getName());
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BluetoothPrintDriver.OpenPrinter(mBluetoothDevice.getAddress());
        setHasOptionsMenu(false);
    }

    @Override
    public void initWidget() {
        data1 = "DATA KE 1";
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

    @OnClick(R.id.label_about1)
    public void klikAbot() {
        Toast.makeText(getActivity(), "KLIK ABOT ".concat(data1), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.label_about2)
    public void klik2(){
        Log.d("KLIK2", "klik2");
    }

}
