package com.tripoin.rmu.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Ginanjar Aji Sanjaya on 5/3/2015.
 */
public class BluetoothUtils {

    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BluetoothDevice mBluetoothDevice;
    private BluetoothSocket mSocket;
    private volatile boolean openedSocket = false;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private volatile boolean stopWorker;
    private int readBufferPosition;
    private byte[] readBuffer;
    private Thread workerThread;
    private Fragment fragmentUtils;

    private BluetoothUtils(Fragment fragment){
        fragmentUtils = fragment;
    }

    private void turnOnBluetooth(){
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
        } else {
            //"android.bluetooth.adapter.action.REQUEST_ENABLE"
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            fragmentUtils.startActivityForResult(intent, 1000);
        }
    }

    private ArrayList<BluetoothDevice> pairedDevices(){
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        final ArrayList<BluetoothDevice> listPaired = new ArrayList<BluetoothDevice>();
        listPaired.addAll(pairedDevices);
        return listPaired;
    }

    private void unpairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("removeBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scanDeviceAround(){
        mBluetoothAdapter.startDiscovery();
    }

    private void cancelScanDeviceAround(){
        mBluetoothAdapter.cancelDiscovery();
    }

    private List<BluetoothDevice> bluetoothDeviceList(){
        return new ArrayList<BluetoothDevice>();
    }


    private void findBT() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Toast.makeText(fragmentUtils.getActivity().getBaseContext(), "No bluetooth adapter available", Toast.LENGTH_SHORT).show();
            }
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                fragmentUtils.startActivityForResult(enableBluetooth, 0);
            }
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    if (device.getName().trim().equalsIgnoreCase("RPP-02")) {
                        mBluetoothDevice = device;
                        Log.d("test", "Bluetooth Device Found");
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

    private void openBT() throws IOException {
        try {
            if(mSocket == null || !openedSocket){
                UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                Log.d("1sdw", mBluetoothDevice.getName());
                mSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(uuid);

                mSocket.connect();
                mOutputStream = mSocket.getOutputStream();
                mInputStream = mSocket.getInputStream();
                beginListenForData();
                Toast.makeText(fragmentUtils.getActivity().getBaseContext(), "Bluetooth Opened", Toast.LENGTH_SHORT).show();
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


    private void sendData(String dataBePrint) throws IOException {
        try {
            // the text typed by the user
            /*String msg = myTextbox.getText().toString();*/
            String msg = dataBePrint;
            msg += "\n";
            mOutputStream.write(msg.getBytes());
            // tell the user data were sent
            /*myLabel.setText("Data Sent");*/
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printData(String dataBePrint) throws IOException {
        findBT();
        openBT();
        sendData(dataBePrint);
    }
}
