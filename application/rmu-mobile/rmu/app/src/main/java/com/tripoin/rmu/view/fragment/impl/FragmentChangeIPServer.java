package com.tripoin.rmu.view.fragment.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
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
import com.tripoin.rmu.view.fragment.base.ABaseNavigationDrawerFragment;

import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 2:33 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class FragmentChangeIPServer extends ABaseNavigationDrawerFragment implements IConnectionPost{


    @InjectView(R.id.label_ip) TextView label_textIp;
    @InjectView(R.id.label_port) TextView label_textPort;
    @InjectView(R.id.lbl_txt_port) TextView lblHeaderPort;
    @InjectView(R.id.lbl_txt_ip) TextView lblHeaderIp;
    @InjectView(R.id.label_test_status) TextView labelTest;
    @InjectView(R.id.test_conn) Button testConn;

    private PropertyUtil propertyUtil;
    private PropertyUtil securityUtil;


    public FragmentChangeIPServer newInstance(String text){
        FragmentChangeIPServer mFragment = new FragmentChangeIPServer();
        return mFragment;
    }

    @Override
    public List<TextView> getContentTextViews() {
        textViews.add(lblHeaderIp);
        textViews.add(lblHeaderPort);
        textViews.add(labelTest);
        return super.getContentTextViews();
    }

    @OnClick(R.id.label_ip)
    public void displayDialogIp(){
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View dialogView = layoutInflater.inflate(R.layout.fragment_change_ip_server, null);
        TextView labelEditIpPort = (TextView) dialogView.findViewById(R.id.lbl_change_ip_port);
        final EditText editIpPort = (EditText) dialogView.findViewById(R.id.edit_ip_port);
        labelEditIpPort.setText("Enter Your IP Address");


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(dialogView);
        editIpPort.setText(label_textIp.getText().toString());
        editIpPort.setSelection(editIpPort.getText().length());

        alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                propertyUtil.saveSingleProperty(PropertyConstant.SERVER_HOST_KEY.toString(), editIpPort.getText().toString());
                label_textIp.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_HOST_KEY.toString()));
                Toast.makeText(getActivity(),"IP Server Change to : " +editIpPort.getText().toString(), Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.label_port)
    public void displayDialogPort(){
        LayoutInflater layoutInflater2 = LayoutInflater.from(getActivity());
        View dialogView2 = layoutInflater2.inflate(R.layout.fragment_change_ip_server, null);
        TextView labelEditIpPort = (TextView) dialogView2.findViewById(R.id.lbl_change_ip_port);
        labelEditIpPort.setText("Enter Your Port");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(dialogView2);
        final EditText editIpPort = (EditText) dialogView2.findViewById(R.id.edit_ip_port);

        editIpPort.setText(label_textPort.getText().toString());
        editIpPort.setSelection(editIpPort.getText().length());

        alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                propertyUtil.saveSingleProperty(PropertyConstant.SERVER_PORT_KEY.toString(), editIpPort.getText().toString());
                label_textPort.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_PORT_KEY.toString()));
                Toast.makeText(getActivity(),"PORT Server Change to : " +editIpPort.getText().toString(), Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.test_conn)
    public void testConnecion(){
        ConnectionRest connectionRest = new ConnectionRest(FragmentChangeIPServer.this) {
            @Override
            public Context getContext() {
                return getActivity();
            }
        };
        connectionRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
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

    @Override
    public String getFragmentTitle() {
        return ViewConstant.FRAGMENT_SERVER_CONFIGURATION.toString();
    }

    @Override
    public void initWidget() {
        propertyUtil = new PropertyUtil(PropertyConstant.PROPERTY_FILE_NAME.toString(), getActivity());
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), getActivity());

        lblHeaderPort.setTextSize(18);
        lblHeaderIp.setTextSize(18);

        label_textIp.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_HOST_KEY.toString()));
        label_textPort.setText(propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_PORT_KEY.toString()));
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_ip_server;
    }
}
