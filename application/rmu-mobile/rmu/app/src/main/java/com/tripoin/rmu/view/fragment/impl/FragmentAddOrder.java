package com.tripoin.rmu.view.fragment.impl;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.tripoin.rmu.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Achmad Fauzi on 4/18/2015 : 1:41 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentAddOrder extends Fragment{

    private EditText tvDisplayDate;
    private DatePicker dpResult;

    private int year;
    private int month;
    private int day;

    Calendar myCalendar = Calendar.getInstance();


    static final int DATE_DIALOG_ID = 999;

    public FragmentAddOrder newInstance(String text){
        FragmentAddOrder mFragment = new FragmentAddOrder();
        /*Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);*/

        return mFragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_order, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    private void setDateTimeField() {
     //.   tvDisplayDate.setOnClickListener(this);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
    }



    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvDisplayDate.setText(sdf.format(myCalendar.getTime()));
    }

}
