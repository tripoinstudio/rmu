package com.tripoin.rmu.feature.bluetooth.api;

/**
 * Created by Achmad Fauzi on 5/8/2015 : 8:45 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface IBluetoothUiHandler {

    public void showUnsupportedMessages();

    public void showBluetoothEnabled();

    public void showBluetoothDisabled();

    public void notifyDataExchanges();

    public void showPairedDevices();
}
