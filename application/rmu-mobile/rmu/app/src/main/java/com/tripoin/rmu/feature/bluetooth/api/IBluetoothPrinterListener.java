package com.tripoin.rmu.feature.bluetooth.api;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import com.tripoin.rmu.model.DTO.print_message.PrintMessageDTO;

/**
 * Created by Achmad Fauzi on 5/6/2015 : 5:22 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface IBluetoothPrinterListener extends IBluetoothUiHandler, IBluetoothDecouplePrint {

    /*Events*/
    public void onBluetoothStateOn();

    public void onBluetoothStartDiscovery();

    public void onBluetoothFinishDiscovery();

    public void onBluetoothDevicesFound(Intent intent);

    public void onBluetoothPaired();

    public void onBluetoothUnPaired();

    public void onCancelDiscovery();

    /*Actions*/
    public void pairDevice(BluetoothDevice bluetoothDevice);

    public void unpairDevice(BluetoothDevice bluetoothDevice);

    public void activeBluetooth();

    public void printSampleMessage();

}
