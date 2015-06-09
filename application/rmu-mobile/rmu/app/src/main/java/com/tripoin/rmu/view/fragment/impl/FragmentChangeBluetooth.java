package com.tripoin.rmu.view.fragment.impl;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

import com.RT_Printer.BluetoothPrinter.BLUETOOTH.BluetoothPrintDriver;
import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.bluetooth.DeviceListAdapter;
import com.tripoin.rmu.feature.bluetooth.api.IBluetoothPrinterListener;
import com.tripoin.rmu.feature.bluetooth.listener.BluetoothReceiver;
import com.tripoin.rmu.model.DTO.print_message.PrintMessageDTO;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.base.ABaseNavigationDrawerFragment;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

import butterknife.InjectView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by Bangkit Pratolo on 4/18/2015 : 2:35 PM.
 * mailto : bangkit.pratolo@sigma.co.id
 */
public class FragmentChangeBluetooth extends ABaseNavigationDrawerFragment implements IBluetoothPrinterListener{

    @InjectView(R.id.tv_status) TextView mStatusTv;
    @InjectView(R.id.switch_enable) Switch mEnableSwitch;
    @InjectView(R.id.btn_test_print) Button mSendBtn;
    @InjectView(R.id.btn_view_paired) Button mPairedBtn;
    @InjectView(R.id.btn_scan) Button mScanBtn;
    @InjectView(R.id.lv_paired) ListView mListView;
    @InjectView(R.id.img_bluetooth) ImageView imgBluetooth;
    @InjectView(R.id.layout_btn_paired_scan) LinearLayout layoutBtnPairedScan;

    private DeviceListAdapter mAdapter;
    private ProgressDialog mProgressDlg;
    private ArrayList<BluetoothDevice> mDeviceList;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice mBluetoothDevice;

    private BluetoothSocket mSocket;
    private OutputStream mOutputStream;
    private InputStream mInputStream;

    private byte[] readBuffer;
    private int readBufferPosition;
    private volatile boolean stopWorker;
    private volatile boolean openedSocket = false;

    private BluetoothReceiver bluetoothReceiver;


    public FragmentChangeBluetooth newInstance(){
        return new FragmentChangeBluetooth();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    private void showToast(String message) {
        Toast.makeText(this.getActivity().getBaseContext(), message, Toast.LENGTH_SHORT).show();
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
        getActivity().unregisterReceiver(bluetoothReceiver);
        super.onDestroy();
    }

    @Override
    public void onBluetoothStateOn() {
        showToast("Enabled");
        showBluetoothEnabled();
    }

    @Override
    public void onBluetoothStartDiscovery() {
        mDeviceList = new ArrayList<BluetoothDevice>();
        mProgressDlg.show();
    }

    @Override
    public void onBluetoothFinishDiscovery() {
        mProgressDlg.dismiss();
    }

    @Override
    public void onBluetoothDevicesFound(Intent intent) {
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
        getActivity().registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED));
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
            if (!mDeviceList.contains(device)) {
                mDeviceList.add(device);
                showToast("Found device " + device.getName());
                notifyDataExchanges();
            }
        }
    }

    @Override
    public void onBluetoothPaired() {
        showToast("Paired");
    }

    @Override
    public void onBluetoothUnPaired() {
        showToast("Unpaired");
    }

    @Override
    public void onCancelDiscovery() {
        mBluetoothAdapter.cancelDiscovery();
    }

    @Override
    public void pairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("createBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unpairDevice(BluetoothDevice device) {
        try {
            Method method = device.getClass().getMethod("removeBond", (Class[]) null);
            method.invoke(device, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openBluetoothConnection() {
        BluetoothPrintDriver.OpenPrinter(mBluetoothDevice.getAddress());
        Log.d("1sdw", mBluetoothDevice.getName());
    }

    @Override
    public void scanBluetoothDevices() {
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


    @OnCheckedChanged(R.id.switch_enable)
    @Override
    public void activeBluetooth() {
        if(mBluetoothAdapter != null ){
            if(mEnableSwitch.isChecked()){
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, 1000);
            }else{
                mBluetoothAdapter.disable();
                final ArrayList<BluetoothDevice> list = new ArrayList<BluetoothDevice>();
                mAdapter.setData(list);
                mListView.setAdapter(mAdapter);
                showBluetoothDisabled();
            }

            if (mBluetoothAdapter.isEnabled()) {
                showBluetoothEnabled();
            } else {
                showBluetoothDisabled();
            }
        }
    }

    @OnClick(R.id.btn_test_print)
    @Override
    public void printSampleMessage() {
        try {
            scanBluetoothDevices();
            openBluetoothConnection();
            try {
                String data =
                        "\n\nPT. Reska Multi Usaha\n"
                        .concat("PRAMIA version 1.0\n\n")
                        .concat("   ---- Print Success ----\n\n");
                if(BluetoothPrintDriver.IsNoConnection()){
                    return;
                }
                BluetoothPrintDriver.Begin();
                BluetoothPrintDriver.ImportData(data);
                BluetoothPrintDriver.ImportData("\r");
                BluetoothPrintDriver.LF();
                BluetoothPrintDriver.LF();
                BluetoothPrintDriver.excute();
                BluetoothPrintDriver.ClearData();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public void printMessage(PrintMessageDTO printMessageDTO) {

    }

    private void beginListenForData() {
        try {
            final Handler handler = new Handler();
            // This is the ASCII code for a newline character
            final byte delimiter = 10;
            stopWorker = false;
            readBufferPosition = 0;
            readBuffer = new byte[1024];

            Thread workerThread = new Thread(new Runnable() {

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

    @Override
    public String getFragmentTitle() {
        return ViewConstant.FRAGMENT_BLUETOOTH_CONFIGURATION.toString();
    }

    @Override
    public void initWidget() {
        bluetoothReceiver = new BluetoothReceiver(this);
        mAdapter = new DeviceListAdapter(rootView.getContext());
        mDeviceList = new ArrayList<BluetoothDevice>();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mProgressDlg = new ProgressDialog(rootView.getContext());
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
            showUnsupportedMessages();
        } else {

            if (mBluetoothAdapter.isEnabled()) {
                showBluetoothEnabled();
            } else {
                showBluetoothDisabled();
            }
        }

        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        getActivity().registerReceiver(bluetoothReceiver, filter);

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_change_bluetooth;
    }

    @Override
    public void showUnsupportedMessages() {
        showBluetoothEnabled();
//        imgBluetooth.setImageDrawable(getResources().getDrawable(R.drawable.ic_bluetooth_red_24dp));
//        mStatusTv.setText("Bluetooth unsupported");
//        mStatusTv.setTextColor(Color.RED);
//        mEnableSwitch.setChecked(false);
//
//        layoutBtnPairedScan.setVisibility(View.INVISIBLE);
////        mPairedBtn.setVisibility(View.INVISIBLE);
//        mScanBtn.setVisibility(View.INVISIBLE);
////        mSendBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showBluetoothEnabled() {
        imgBluetooth.setImageDrawable(getResources().getDrawable(R.drawable.ic_bluetooth_audio_white_24dp));
        mStatusTv.setText("Bluetooth is On");
        mStatusTv.setTextColor(Color.WHITE);
        mEnableSwitch.setChecked(true);

        layoutBtnPairedScan.setVisibility(View.VISIBLE);
        animate(layoutBtnPairedScan).alpha(0.5f).alpha(1.0f);
//        mPairedBtn.setVisibility(View.VISIBLE);
        mScanBtn.setVisibility(View.VISIBLE);
        animate(mScanBtn).alpha(0.5f).alpha(1.0f);
//        mSendBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBluetoothDisabled() {
        showBluetoothEnabled();
//        imgBluetooth.setImageDrawable(getResources().getDrawable(R.drawable.ic_bluetooth_red_24dp));
//        mStatusTv.setText("Bluetooth is Off");
//        mStatusTv.setTextColor(Color.RED);
//        mEnableSwitch.setChecked(false);
//
////        layoutBtnPairedScan.setVisibility(View.INVISIBLE);
//        animate(layoutBtnPairedScan).alpha(0.5f).alpha(0f);
////        mPairedBtn.setVisibility(View.INVISIBLE);
////        mScanBtn.setVisibility(View.INVISIBLE);
//        animate(mScanBtn).alpha(0.5f).alpha(0f);
////        mSendBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void notifyDataExchanges() {
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_view_paired)
    @Override
    public void showPairedDevices() {
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
                        notifyDataExchanges();
                    } else {
                        showToast("Pairing...");
                        list.add(device);
                        pairDevice(device);
                        notifyDataExchanges();
                    }
                }
            });
            mAdapter.setData(list);
            mListView.setAdapter(mAdapter);
        }
    }

    @OnClick(R.id.btn_scan)
    public void scanDevice(){
        if(mBluetoothAdapter != null){
            mBluetoothAdapter.startDiscovery();
        }
    }

    public class MyScaler extends ScaleAnimation {

        private View mView;

        private LinearLayout.LayoutParams mLayoutParams;

        private int mMarginBottomFromY, mMarginBottomToY;

        private boolean mVanishAfter = false;

        public MyScaler(float fromX, float toX, float fromY, float toY, int duration, View view,
                        boolean vanishAfter) {
            super(fromX, toX, fromY, toY);
            setDuration(duration);
            mView = view;
            mVanishAfter = vanishAfter;
            mLayoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            int height = mView.getHeight();
            mMarginBottomFromY = (int) (height * fromY) + mLayoutParams.bottomMargin - height;
            mMarginBottomToY = (int) (0 - ((height * toY) + mLayoutParams.bottomMargin)) - height;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                int newMarginBottom = mMarginBottomFromY
                        + (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime);
                mLayoutParams.setMargins(mLayoutParams.leftMargin, mLayoutParams.topMargin,
                        mLayoutParams.rightMargin, newMarginBottom);
                mView.getParent().requestLayout();
            } else if (mVanishAfter) {
                mView.setVisibility(View.VISIBLE);
            }
        }
    }

}
