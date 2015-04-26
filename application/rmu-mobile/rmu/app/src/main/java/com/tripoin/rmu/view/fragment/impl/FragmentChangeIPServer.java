package com.tripoin.rmu.view.fragment.impl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.util.impl.PropertyUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 2:33 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentChangeIPServer extends Fragment {

    private EditText editIP;
    private Button btnOk;
    private TextView label_textIp, lblTxt;
    private ImageView imgView;

    private PropertyUtil propertyUtil;

    private final static String IPSETTING = "ip-setting.txt";

    public FragmentChangeIPServer newInstance(String text){
        FragmentChangeIPServer mFragment = new FragmentChangeIPServer();
        /*Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);*/
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_ip_server, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        label_textIp = (TextView) rootView.findViewById(R.id.label_ip);
        lblTxt = (TextView) rootView.findViewById(R.id.lbl_txt_ip);
        Typeface fontFace = Typeface.createFromAsset(lblTxt.getResources().getAssets(),"font/Roboto-Light.ttf");
        lblTxt.setTypeface(fontFace);
        lblTxt.setTextSize(18);

        label_textIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(rootView.getContext());
                View dialogView = layoutInflater.inflate(R.layout.fragment_change_ip_server, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(rootView.getContext());
                alertDialogBuilder.setView(dialogView);

                editIP = (EditText) dialogView.findViewById(R.id.edit_ip);
                editIP.setText(label_textIp.getText().toString());
                InputFilter[] filters = new InputFilter[1];
                filters[0] = new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if (end > start){
                            String destTxt = dest.toString();
                            String resultingTxt = destTxt.substring(0, dstart) +
                                    source.subSequence(start, end) +
                                    destTxt.substring(dend);
                            if (!resultingTxt.matches ("^\\d{1,3}(\\." +
                                    "(\\d{1,4}(\\.(\\d{1,4}(\\.(\\d{1,4})?)?)?)?)?)?")) {
                                return "";
                            } else {
                                String[] splits = resultingTxt.split("\\.");
                                for (int i=0; i<splits.length; i++) {
                                    if (Integer.valueOf(splits[i]) > 255) {
                                        return "";
                                    }
                                }
                            }
                        }
                        return null;
                    }
                };
                editIP.setFilters(filters);

                alertDialogBuilder.setCancelable(false).setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        propertyUtil.saveSingleProperty("IP_CONFIG", editIP.getText().toString());
                        label_textIp.setText(propertyUtil.getValuePropertyMap("IP_CONFIG"));
                        Toast.makeText(rootView.getContext(),"IP Server Change to : " +editIP.getText().toString(), Toast.LENGTH_SHORT).show();
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

        propertyUtil = new PropertyUtil(IPSETTING , rootView.getContext());

        label_textIp.setText(propertyUtil.getValuePropertyMap("IP_CONFIG"));

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }
}
