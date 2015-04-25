package com.tripoin.rmu.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.tripoin.rmu.R;
import com.tripoin.rmu.util.enumeration.NetworkConstant;

import org.apache.http.conn.util.InetAddressUtils;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.Enumeration;


/**
 * Created by Achmad Fauzi on 9/4/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * This class is used to check network connectivity
 */
public class NetworkConnectivity {

    private Activity activity;
    private Service service;

    public NetworkConnectivity( Activity activity , Service service) {
        if( activity != null ){
            this.activity = activity;
        }else{
            this.service = service;
        }

    }

    /**
     * Get connectivity status as int
     * @param context Context
     * @return int
     */
    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return Integer.valueOf( NetworkConstant.TYPE_WIFI.toString() );

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return Integer.valueOf( NetworkConstant.TYPE_MOBILE.toString() );
        }
        return Integer.valueOf( NetworkConstant.TYPE_NOT_CONNECTED.toString() );
    }

    /**
     * Get connectivity status as String
     * @return String
     */
    public String getConnectivityStatusString() {
        int conn;
        if ( activity != null ){
            conn = NetworkConnectivity.getConnectivityStatus(activity.getApplicationContext());
        }else{
            conn = NetworkConnectivity.getConnectivityStatus(service.getApplicationContext());
        }
        String status = null;
        if (conn == Integer.valueOf( NetworkConstant.TYPE_WIFI.toString() )) {
            status = NetworkConstant.WIFI_ENABLED.toString();
        } else if (conn == Integer.valueOf( NetworkConstant.TYPE_MOBILE.toString() ) ) {
            status = NetworkConstant.MOBILE_DATA_ENABLED.toString();
        } else if (conn == Integer.valueOf( NetworkConstant.TYPE_NOT_CONNECTED.toString() ) ) {
            status = NetworkConstant.NOT_CONNECTED_TO_INTERNET.toString();
        }
        return status;
    }

    /**
     * Checks networking status.
      * @return boolean
     */
    public boolean checkConnectivity(){
        boolean enabled = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())){
            enabled = false;
            AlertDialog.Builder builder = new AlertDialog.Builder( activity );
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage( activity.getString(R.string.string_noconnection) );
            builder.setCancelable(false);
            builder.setNeutralButton(R.string.string_ok, null);
            builder.setTitle( activity.getString(R.string.string_error));
            builder.create().show();
        }
        return enabled;
    }

    /**
     * Checks networking status at main activity if false application will exit.
     * @return boolean
     */
    public boolean checkConnectivityMain(){
        boolean enabled = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())){
            enabled = false;
            AlertDialog.Builder builder = new AlertDialog.Builder( activity );
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage( activity.getString(R.string.string_noconnection) );
            builder.setCancelable(false);
            builder.setNeutralButton(R.string.string_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                }
            });
            builder.setTitle( activity.getString(R.string.string_error));
            builder.create().show();
        }
        return enabled;
    }

    public boolean checkConnectivityNoUI(){
        boolean enabled = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())){
            enabled = false;
        }
        return enabled;
    }

    /**
     * Checks networking status.
     * @return boolean
     */
    public boolean checkConnectivityBackground(){
        boolean enabled = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())){
            enabled = false;
        }
        return enabled;
    }

    /**
     * Checks networking status.
     * @return boolean
     */
    public boolean checkConnectivityService(){
        boolean enabled = true;
        ConnectivityManager connectivityManager = (ConnectivityManager) service.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())){
            enabled = false;
        }
        return enabled;
    }

    /**
     * Get Wifi Ip Address when connected on Wifi
     * @return String
     */
    public String getWifiIpAddress() {
        WifiManager wifiManager ;
        if( activity != null ){
            wifiManager = (WifiManager) activity.getSystemService(Context.WIFI_SERVICE);
        }else{
            wifiManager = (WifiManager) service.getSystemService(Context.WIFI_SERVICE);
        }
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        // Convert little-endian to big-endianif needed
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }
        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();
        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("WIFI IP", "Unable to get host address.");
            ipAddressString = "-";
        }
        return ipAddressString;
    }

    /**
     * Get mobile IP Address when connected to Mobile Connection
     * @return String
     */
    public String getMobileIPAddress() {
        String ipAddressString = null;
        try {
            for ( Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for ( Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if ( !inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(inetAddress.getHostAddress()) ) {
                        ipAddressString = inetAddress.getHostAddress();
                    }
                }
            }
        } catch ( SocketException ex ) {
            Log.e( "MOBILE IP", "Exception in Get IP Address: " + ex.toString());
            ipAddressString = "-";
        }
        return ipAddressString;
    }


}