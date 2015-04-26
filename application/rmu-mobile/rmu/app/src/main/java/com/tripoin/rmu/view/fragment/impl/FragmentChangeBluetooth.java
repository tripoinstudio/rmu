package com.tripoin.rmu.view.fragment.impl;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Bangkit Pratolo on 4/18/2015 : 2:35 PM.
 * mailto : bangkit.pratolo@sigma.co.id
 */
public class FragmentChangeBluetooth extends Fragment {
    private TextView mStatusTv;
    private Button mActivateBtn;
    private Button mPairedBtn;
    private Button mScanBtn;
    //    private Button mCloseBtn;
    private Button mSendBtn;
    //    private Button mOpenBtn;
   /* private TextView myLabel;
    private EditText myTextbox;*/
    private ListView mListView;
    private DeviceListAdapter mAdapter;

    private ProgressDialog mProgressDlg;
    private ArrayList<BluetoothDevice> mDeviceList = new ArrayList<BluetoothDevice>();
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBluetoothDevice;

    private BluetoothSocket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private Thread workerThread;

    private byte[] readBuffer;
    private int readBufferPosition;
    private int counter;
    private volatile boolean stopWorker;
    private volatile boolean openedSocket = false;


    public FragmentChangeBluetooth newInstance(String text){
        FragmentChangeBluetooth mFragment = new FragmentChangeBluetooth();
        /*Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);*/
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_change_bluetooth, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));

        mStatusTv           = (TextView) rootView.findViewById(R.id.tv_status);
        mActivateBtn        = (Button) rootView.findViewById(R.id.btn_enable);
        mSendBtn            = (Button) rootView.findViewById(R.id.btn_send_device);
//        mCloseBtn           = (Button) rootView.findViewById(R.id.btn_close_device);
        mPairedBtn          = (Button) rootView.findViewById(R.id.btn_view_paired);
        mScanBtn            = (Button) rootView.findViewById(R.id.btn_scan);
        mListView		    = (ListView) rootView.findViewById(R.id.lv_paired);
        mAdapter		    = new DeviceListAdapter(rootView.getContext());
        /*myLabel             = (TextView) rootView.findViewById(R.id.label);
        myTextbox           = (EditText) rootView.findViewById(R.id.entry);*/
//        mOpenBtn            = (Button) rootView.findViewById(R.id.btn_open_device);
        mBluetoothAdapter   = BluetoothAdapter.getDefaultAdapter();
        mProgressDlg        = new ProgressDialog(rootView.getContext());

        mProgressDlg.setMessage("Scanning...");
        mProgressDlg.setCancelable(false);
        mProgressDlg.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mBluetoothAdapter.cancelDiscovery();
            }
        });

        if (mBluetoothAdapter == null) {
            showUnsupported();
        } else {
            mPairedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
                    if (pairedDevices == null || pairedDevices.size() == 0) {
                        showToast("No Paired Devices Found");
                        final ArrayList<BluetoothDevice> list = new ArrayList<BluetoothDevice>();
                        list.addAll(pairedDevices);
                        mAdapter.setData(list);
                        mListView.setAdapter(mAdapter);
                    } else {
                        final ArrayList<BluetoothDevice> list = new ArrayList<BluetoothDevice>();
                        list.addAll(pairedDevices);
                        mAdapter.setListener(new DeviceListAdapter.OnPairButtonClickListener() {
                            @Override
                            public void onPairButtonClick(int position) {
                                BluetoothDevice device = list.get(position);
                                if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                                    showToast("UnPairing...");
                                    list.remove(device);
                                    unpairDevice(device);
                                    updateUI();
                                } else {
                                    showToast("Pairing...");
                                    list.add(device);
                                    pairDevice(device);
                                    updateUI();
                                }
                            }
                        });
                        mAdapter.setData(list);
                        mListView.setAdapter(mAdapter);
                    }
                }
            });

            mScanBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    mBluetoothAdapter.startDiscovery();
                }
            });

            mActivateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.disable();
                        final ArrayList<BluetoothDevice> list = new ArrayList<BluetoothDevice>();
                        mAdapter.setData(list);
                        mListView.setAdapter(mAdapter);
                        showDisabled();
                    } else {
                        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intent, 1000);

                    }
                }
            });

//            mOpenBtn.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
//                    try {
//                        findBT();
//                        openBT();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

//            mCloseBtn.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
//                    try {
//                        closeBT();
//                    } catch (IOException ex) {
//                    }
//                }
//            });

            mSendBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    try {
                        findBT();
                        openBT();
                        sendData();
                    } catch (IOException ex) {
                    }
                }
            });

            if (mBluetoothAdapter.isEnabled()) {
                showEnabled();
            } else {
                showDisabled();
            }
        }

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        getActivity().registerReceiver(mReceiver, filter);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    private void showUnsupported() {
        mStatusTv.setText("Bluetooth is unsupported by this device");

        mActivateBtn.setText("Enable");
        mActivateBtn.setEnabled(false);

        mPairedBtn.setEnabled(false);
        mScanBtn.setEnabled(false);
    }

    private void showToast(String message) {
        Toast.makeText(this.getActivity().getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void showEnabled() {
        mStatusTv.setText("Bluetooth is On");
        mStatusTv.setTextColor(Color.BLUE);

        mActivateBtn.setText("Disable");
        mActivateBtn.setEnabled(true);

        mPairedBtn.setEnabled(true);
        mScanBtn.setEnabled(true);
//        mOpenBtn.setEnabled(true);
        mSendBtn.setEnabled(true);
//        mCloseBtn.setEnabled(true);\
    }

    private void showDisabled() {
        mStatusTv.setText("Bluetooth is Off");
        mStatusTv.setTextColor(Color.RED);

        mActivateBtn.setText("Enable");
        mActivateBtn.setEnabled(true);

        mPairedBtn.setEnabled(false);
        mScanBtn.setEnabled(false);
//        mOpenBtn.setEnabled(false);
        mSendBtn.setEnabled(false);
//        mCloseBtn.setEnabled(false);
    }

    @Override
    public void onPause() {
        if (mBluetoothAdapter != null) {
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private void pairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unpairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("removeBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                if (state == BluetoothAdapter.STATE_ON) {
                    showToast("Enabled");
                    showEnabled();
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                mDeviceList = new ArrayList<BluetoothDevice>();

                mProgressDlg.show();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                mProgressDlg.dismiss();
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                /*bangkit*/
                mAdapter.setData(mDeviceList);
                mAdapter.setListener(new DeviceListAdapter.OnPairButtonClickListener() {

                    @Override
                    public void onPairButtonClick(int position) {
                        BluetoothDevice device = mDeviceList.get(position);
                        if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                            unpairDevice(device);
                        } else {
                            showToast("Pairing...");
                            pairDevice(device);
                        }
                    }
                });

                mListView.setAdapter(mAdapter);
                getActivity().registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED));
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    if (mDeviceList.contains(device) == false) {
                        mDeviceList.add(device);
                        showToast("Found device " + device.getName());
                        updateUI();
                    }
                }
                /*end*/
            }else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                final int state 		= intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
                final int prevState	    = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR);
                if (state == BluetoothDevice.BOND_BONDED && prevState == BluetoothDevice.BOND_BONDING) {
                    showToast("Paired");
                } else if (state == BluetoothDevice.BOND_NONE && prevState == BluetoothDevice.BOND_BONDED){
                    showToast("Unpaired");
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    };

    private void updateUI() {
        mAdapter.notifyDataSetChanged();
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
                showToast("Bluetooth Opened");
                openedSocket = true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findBT() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBluetoothAdapter == null) {
                showToast("No bluetooth adapter available");
            }
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
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

    private void sendData() throws IOException {

        try {
            // the text typed by the user
            /*String msg = myTextbox.getText().toString();*/
            String msg = "Printer Success Connected..!";
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

    private void closeBT() throws IOException {
        try {
            stopWorker = true;
            mOutputStream.close();
            mInputStream.close();
            mSocket.close();
            showToast("Bluetooth Closed");
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
