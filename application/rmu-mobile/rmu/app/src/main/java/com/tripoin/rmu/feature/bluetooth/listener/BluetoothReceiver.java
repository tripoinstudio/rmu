package com.tripoin.rmu.feature.bluetooth.listener;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tripoin.rmu.feature.bluetooth.api.IBluetoothPrinterListener;

/**
 * Created by Achmad Fauzi on 5/7/2015 : 11:03 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class BluetoothReceiver extends BroadcastReceiver {

    private IBluetoothPrinterListener iBluetoothPrinterListener;

    public BluetoothReceiver(IBluetoothPrinterListener iBluetoothPrinterListener) {
        this.iBluetoothPrinterListener = iBluetoothPrinterListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
            if (state == BluetoothAdapter.STATE_ON) {
                iBluetoothPrinterListener.onBluetoothStateOn();
            }
        } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
            iBluetoothPrinterListener.onBluetoothStartDiscovery();
        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            iBluetoothPrinterListener.onBluetoothFinishDiscovery();
        } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            iBluetoothPrinterListener.onBluetoothDevicesFound(intent);
        }else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
            final int state 		= intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
            final int prevState	    = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR);
            if (state == BluetoothDevice.BOND_BONDED && prevState == BluetoothDevice.BOND_BONDING) {
                iBluetoothPrinterListener.onBluetoothPaired();
            } else if (state == BluetoothDevice.BOND_NONE && prevState == BluetoothDevice.BOND_BONDED){
                iBluetoothPrinterListener.onBluetoothUnPaired();
            }
            iBluetoothPrinterListener.notifyDataExchanges();
        }
    }
}
