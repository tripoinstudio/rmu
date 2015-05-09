package com.tripoin.rmu.feature.bluetooth.api;

import com.tripoin.rmu.model.DTO.print_message.PrintMessageDTO;

/**
 * Created by Achmad Fauzi on 5/8/2015 : 10:36 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface IBluetoothDecouplePrint {

    public void scanBluetoothDevices();

    public void openBluetoothConnection();

    public void printMessage(PrintMessageDTO printMessageDTO);

}
