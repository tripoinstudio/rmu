package com.tripoin.rmu.feature.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;

import com.tripoin.rmu.feature.bluetooth.api.IBluetoothDecouplePrint;
import com.tripoin.rmu.feature.bluetooth.api.IBluetoothPrinterListener;
import com.tripoin.rmu.feature.bluetooth.listener.BluetoothReceiver;
import com.tripoin.rmu.model.DTO.print_message.PrintMessageDTO;
import com.tripoin.rmu.view.fragment.impl.DeviceListAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Achmad Fauzi on 5/7/2015 : 11:05 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class BluetoothEngine implements IBluetoothPrinterListener{

    private Activity activity;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBluetoothDevice;

    private BluetoothSocket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private Thread workerThread;

    private byte[] readBuffer;
    private int readBufferPosition;
    private volatile boolean stopWorker;
    private volatile boolean openedSocket = false;

    BluetoothReceiver bluetoothReceiver;

    public BluetoothEngine(Activity activity) {
        this.activity = activity;
        bluetoothReceiver = new BluetoothReceiver(this);

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);


        activity.registerReceiver(bluetoothReceiver, filter);
    }

    @Override
    public void scanBluetoothDevices() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Log.d("BLUETOOTH ENGINE ADAPTER", "NULL");
            }
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                activity.startActivityForResult(enableBluetooth, 0);
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
    }

    @Override
    public void openBluetoothConnection() {
        try {
            if(mSocket == null || !openedSocket){
                UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                Log.d("1sdw", mBluetoothDevice.getName());
                mSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(uuid);

                mSocket.connect();
                mOutputStream = mSocket.getOutputStream();
                mInputStream = mSocket.getInputStream();
                beginListenForData();
                openedSocket = true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void beginListenForData() {
        try {
            final Handler handler = new Handler();
            // This is the ASCII code for a newline character
            final byte delimiter = 10;
            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            workerThread = new Thread(new Runnable() {

                public void run() {
                    while (!Thread.currentThread().isInterrupted() && !stopWorker) {
                        try {
                            int bytesAvailable = mInputStream.available();
                            if (bytesAvailable > 0) {
                                byte[] packetBytes = new byte[bytesAvailable];
                                mInputStream.read(packetBytes);
                                for (int i = 0; i < bytesAvailable; i++) {
                                    byte b = packetBytes[i];
                                    if (b == delimiter) {
                                        byte[] encodedBytes = new byte[readBufferPosition];
                                        System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                        final String data = new String(encodedBytes, "US-ASCII");
                                        readBufferPosition = 0;
                                        handler.post(new Runnable() {
                                            public void run() {
                                                /*myLabel.setText(data);*/
                                                Log.d("dataEncoding",data);
                                            }
                                        });
                                    } else {
                                        readBuffer[readBufferPosition++] = b;
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            stopWorker = true;
                        }
                    }
                }
            });
            workerThread.start();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printMessage(PrintMessageDTO printMessageDTO) {
        //scanBluetoothDevices();
        openBluetoothConnection();
        try {
            String data =
                    "\n\nPT. Reska Multi Usaha\n"
                            .concat("eRestorasi version 1.0\n\n")
                            .concat("   ---- Print Success ----\n\n");
            mOutputStream.write(data.getBytes());
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBluetoothStateOn() {

    }

    @Override
    public void onBluetoothStartDiscovery() {

    }

    @Override
    public void onBluetoothFinishDiscovery() {

    }

    @Override
    public void onBluetoothDevicesFound(Intent intent) {

    }

    @Override
    public void onBluetoothPaired() {

    }

    @Override
    public void onBluetoothUnPaired() {

    }

    @Override
    public void onCancelDiscovery() {

    }

    @Override
    public void pairDevice(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void unpairDevice(BluetoothDevice bluetoothDevice) {

    }

    @Override
    public void activeBluetooth() {

    }

    @Override
    public void printSampleMessage() {

    }

    @Override
    public void showUnsupportedMessages() {

    }

    @Override
    public void showBluetoothEnabled() {

    }

    @Override
    public void showBluetoothDisabled() {

    }

    @Override
    public void notifyDataExchanges() {

    }

    @Override
    public void showPairedDevices() {

    }
}
