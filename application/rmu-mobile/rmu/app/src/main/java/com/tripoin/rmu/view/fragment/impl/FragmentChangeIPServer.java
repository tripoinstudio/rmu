package com.tripoin.rmu.view.fragment.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.tripoin.rmu.R;

import com.tripoin.rmu.model.base.impl.BaseRESTDTO;
import com.tripoin.rmu.rest.api.IConnectionPost;
import com.tripoin.rmu.rest.impl.ConnectionRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 2:33 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentChangeIPServer extends Fragment implements IConnectionPost{

    private EditText editIpPort;
    private TextView label_textIp, lblHeaderIp, label_textPort, lblHeaderPort, labelEditIpPort, labelNotif, labelTest;
    private Button testConn;

    private PropertyUtil propertyUtil;
    private PropertyUtil securityUtil;
    private Typeface fontFace, fontFace2, fontFace3;


    public FragmentChangeIPServer newInstance(String text){
        FragmentChangeIPServer mFragment = new FragmentChangeIPServer();
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_ip_server, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        propertyUtil = new PropertyUtil(PropertyConstant.PROPERTY_FILE_NAME.toString(), rootView.getContext());
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), rootView.getContext());

        label_textIp = (TextView) rootView.findViewById(R.id.label_ip);
        label_textPort = (TextView) rootView.findViewById(R.id.label_port);

        lblHeaderPort = (TextView) rootView.findViewById(R.id.lbl_txt_port);
        fontFace2 = Typeface.createFromAsset(lblHeaderPort.getResources().getAssets(),"font/Roboto-Light.ttf");
        lblHeaderPort.setTypeface(fontFace2);
        lblHeaderPort.setTextSize(18);

        lblHeaderIp = (TextView) rootView.findViewById(R.id.lbl_txt_ip);
        fontFace = Typeface.createFromAsset(lblHeaderIp.getResources().getAssets(),"font/Roboto-Light.ttf");
        lblHeaderIp.setTypeface(fontFace);
        lblHeaderIp.setTextSize(18);

        labelTest = (TextView) rootView.findViewById(R.id.label_test_status);
        fontFace = Typeface.createFromAsset(labelTest.getResources().getAssets(), "font/Roboto-Light.ttf");
        labelTest.setTypeface(fontFace3);

        label_textIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_change_ip_server, null);

                labelEditIpPort = (TextView) dialogView.findViewById(R.id.lbl_change_ip_port);
                labelEditIpPort.setText("Enter Your IP Address");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editIpPort = (EditText) dialogView.findViewById(R.id.edit_ip_port);
                editIpPort.setText(label_textIp.getText().toString());
                editIpPort.setSelection(editIpPort.getText().length());

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty(PropertyConstant.SERVER_HOST_KEY.toString(), editIpPort.getText().toString());
                        label_textIp.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_HOST_KEY.toString()));
                        Toast.makeText(rootView.getContext(),"IP Server Change to : " +editIpPort.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();

            }
        });

        label_textPort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater2 = LayoutInflater.from(rootView.getContext());
                View dialogView2 = layoutInflater2.inflate(R.layout.fragment_change_ip_server, null);

                labelEditIpPort = (TextView) dialogView2.findViewById(R.id.lbl_change_ip_port);
                labelEditIpPort.setText("Enter Your Port");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView2);

                editIpPort = (EditText) dialogView2.findViewById(R.id.edit_ip_port);
                editIpPort.setText(label_textPort.getText().toString());
                editIpPort.setSelection(editIpPort.getText().length());

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty(PropertyConstant.SERVER_PORT_KEY.toString(), editIpPort.getText().toString());
                        label_textPort.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_PORT_KEY.toString()));
                        Toast.makeText(rootView.getContext(),"PORT Server Change to : " +editIpPort.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertD = alertDialogBuilder.create();
                alertD.show();
            }
        });

        label_textIp.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_HOST_KEY.toString()));
        label_textPort.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_PORT_KEY.toString()));

        testConn = (Button) rootView.findViewById(R.id.test_conn);
        testConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionRest connectionRest = new ConnectionRest(FragmentChangeIPServer.this) {
                    @Override
                    public Context getContext() {
                        return rootView.getContext();
                    }
                };
                connectionRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onPostDelegate(Object objectResult) {
        if (objectResult != null){
            BaseRESTDTO baseRESTDTO = (BaseRESTDTO) objectResult;
            if( baseRESTDTO.getErr_code().equals(ViewConstant.ZERO.toString())){
                labelTest.setText( baseRESTDTO.getErr_msg() );
                labelTest.setTextColor(getResources().getColor(R.color.green_base));
            }else{
                setFailedStatus();
            }
        }else{
            setFailedStatus();
        }
    }

    private void setFailedStatus(){
        labelTest.setText( "Connection Failed" );
        labelTest.setTextColor(getResources().getColor(R.color.red_dark_holo));
    }

}
