package com.tripoin.rmu.feature.bluetooth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;
import com.tripoin.rmu.feature.bluetooth.listener.BluetoothReceiver;
import com.tripoin.rmu.model.DTO.print_message.PrintMessageDTO;
import com.tripoin.rmu.model.persist.OrderTempModel;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.ui.PaddingHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Achmad Fauzi on 5/7/2015 : 11:05 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class BluetoothEngine{

    private Activity activity;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBluetoothDevice;

    private BluetoothSocket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;

    private byte[] readBuffer;
    private int readBufferPosition;
    private volatile boolean stopWorker;
    private volatile boolean openedSocket = false;


    private ProgressDialog mProgressDlg;
    private ArrayList<BluetoothDevice> mDeviceList;
    private DeviceListAdapter mAdapter;

    BluetoothReceiver bluetoothReceiver;

    public BluetoothEngine(Activity activity) {
        this.activity = activity;
        bluetoothReceiver = new BluetoothReceiver();

        /*IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);


        activity.registerReceiver(bluetoothReceiver, filter);*/


    }

    private void scanBluetoothDevices() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                Log.d("BLUETOOTH ENGINE", "NULL");
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
    public boolean checkOnBluetooth(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter.isEnabled();
    }

    public boolean openBluetoothConnection() {
        scanBluetoothDevices();
        return (BluetoothPrintDriver.OpenPrinter(mBluetoothDevice.getAddress()) && mBluetoothAdapter.isEnabled());
    }

    private void beginListenForData() {
        try {
            final Handler handler = new Handler();
            // This is the ASCII code for a newline character
            final byte delimiter = 10;
            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            Thread workerThread= new Thread(new Runnable() {

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


    public String templateMessageDto(PrintMessageDTO printMessageDTO) {
        scanBluetoothDevices();
        openBluetoothConnection();
        String print = "";
        try {
            String orderNoPrintData = "Order No : ";
            orderNoPrintData = orderNoPrintData.concat(printMessageDTO.getOrderNo())
                                               .concat(ViewConstant.PRINT_DASH.toString());

            String menuPrintData = "";
            for(OrderTempModel orderTempModel : printMessageDTO.getMessageItemDTOs()){
                int countMenuName = orderTempModel.getMenuName().length();
                String menuNamePadding = orderTempModel.getMenuName();
                if(countMenuName > 11){
                    menuNamePadding = menuNamePadding.substring(0, 11);
                }
                menuNamePadding = menuNamePadding.concat("(").concat(orderTempModel.getQuantity()).concat(")");
                menuNamePadding = new PaddingHelper().rightPaddingString(menuNamePadding, 15, " ");

                String pricePadding = new PaddingHelper().leftPaddingString(orderTempModel.getPrice(), 9, " ");

                menuPrintData = menuPrintData.concat(menuNamePadding)
                        .concat("|  Rp.")
                        .concat(pricePadding)
                        .concat(ViewConstant.CURRENCY_PATTERN.toString());
            }

            String totalOrderPadding = printMessageDTO.getTotal();
            totalOrderPadding = new PaddingHelper().leftPaddingString(totalOrderPadding, 9, " ");

            String totalPrintData = ViewConstant.PRINT_DASH.toString()
                    .concat("\nTotal          :  Rp.")
                    .concat(totalOrderPadding)
                    .concat(ViewConstant.CURRENCY_PATTERN.toString()).concat("\n");

            print = ViewConstant.PRINT_HEADER.toString().concat(orderNoPrintData).concat(menuPrintData).concat(totalPrintData).concat(ViewConstant.PRINT_FOOTER.toString());

            /*mOutputStream.write(print.getBytes());*/
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return print;
    }

    public void printTemplateString(String s){
        BluetoothPrintDriver.Begin();
        BluetoothPrintDriver.ImportData(s);
        BluetoothPrintDriver.ImportData("\r");
        BluetoothPrintDriver.LF();
        BluetoothPrintDriver.LF();
        BluetoothPrintDriver.excute();
        BluetoothPrintDriver.ClearData();
    }

    public void requestOnBluetooth(){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBtIntent, 2);
    }

    public boolean isNoConnection(){
        return BluetoothPrintDriver.IsNoConnection();
    }


    public void onBluetoothStateOn() {

    }


    public void onBluetoothStartDiscovery() {
        mDeviceList = new ArrayList<BluetoothDevice>();
        mProgressDlg.show();
    }


    public void onBluetoothFinishDiscovery() {
        mProgressDlg.dismiss();
    }


    public void onBluetoothDevicesFound(Intent intent) {
        mAdapter.setData(mDeviceList);
        mAdapter.setListener(new DeviceListAdapter.OnPairButtonClickListener() {
            @Override
            public void onPairButtonClick(int position) {
                BluetoothDevice device = mDeviceList.get(position);
                if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                    unpairDevice(device);
                } else {
                    Log.d("PAIRING","Pairing...");
                    pairDevice(device);
                }
            }
        });
    }


    public void onBluetoothPaired() {

    }


    public void onBluetoothUnPaired() {

    }


    public void onCancelDiscovery() {
        mBluetoothAdapter.cancelDiscovery();
    }


    public void pairDevice(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(bluetoothDevice, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void unpairDevice(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("removeBond", (Class[]) null);
            method.invoke(bluetoothDevice, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void activeBluetooth() {
        Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBluetooth, 0);
    }


    public String templateSampleMessage() {
        return "\n\nPT. Reska Multi Usaha\n"
                .concat("PRAMIA version 1.0\n\n")
                .concat("   ---- Print Success ----\n\n");
    }

    public void finishAplication(){
        BluetoothPrintDriver.close();
        activity.finish();
    }


    public void showUnsupportedMessages() {

    }


    public void showBluetoothEnabled() {

    }


    public void showBluetoothDisabled() {

    }


    public void notifyDataExchanges() {

    }


    public void showPairedDevices() {

    }


}
