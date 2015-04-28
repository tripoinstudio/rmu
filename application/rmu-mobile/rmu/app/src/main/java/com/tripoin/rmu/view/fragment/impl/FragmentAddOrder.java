package com.tripoin.rmu.view.fragment.impl;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.tripoin.rmu.R;

/**
 * Created by Syahrial Fandrianah on 4/18/2015 : 1:41 AM.
 * mailto : sfandrianah2@gmail.com
 */
public class FragmentAddOrder extends Fragment {

    private String array_spinner_carriage[];
    private static final String[] COUNTRIES = new String[] { "Gerbong 1",
            "Gerbong 2", "Gerbong 3", "Gerbong 4", "Gerbong 5" };
    private static final String[] SEATS = new String[] { "Seat 1",
            "Seat 2", "Seat 3", "Seat 4", "Seat 5" };
    View rootView;
    private Typeface myFont;
    private Spinner mySpinner;

    private Typeface myFontSeat;
    private Spinner mySpinnerSeat;

    public FragmentAddOrder newInstance(String text){
        FragmentAddOrder mFragment = new FragmentAddOrder();

        /*Bundle mBundle = new Bundle();
        mBundle.putString(TEXT_FRAGMENT, text);
        mFragment.setArguments(mBundle);*/

        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_order, container, false);
        TextView txus=(TextView)rootView.findViewById(R.id.tx_username);
        Typeface faces=Typeface.createFromAsset(txus.getResources().getAssets(),"font/Roboto-Light.ttf");
        txus.setTypeface(faces);
        txus.setTextSize(15);
//        txus.setTypeface(null, Typeface.BOLD);
        TextView txus2=(TextView)rootView.findViewById(R.id.tx_dates);
        Typeface faces2=Typeface.createFromAsset(txus2.getResources().getAssets(),"font/Roboto-Light.ttf");
        txus2.setTypeface(faces2);
        txus2.setTextSize(15);
//        txus2.setTypeface(null,Typeface.BOLD);

        Button bt_bayar =(Button)rootView.findViewById(R.id.bt_bayar);
        bt_bayar.setTypeface(faces2);

        mySpinner = (Spinner) rootView.findViewById(R.id.spinner_carriage);
        myFont = Typeface.createFromAsset(mySpinner.getResources().getAssets(), "font/Roboto-Light.ttf");
        MyArrayAdapter ma = new MyArrayAdapter(rootView.getContext());
        mySpinner.setAdapter(ma);

        mySpinnerSeat = (Spinner) rootView.findViewById(R.id.spinner_seat);
        myFontSeat = Typeface.createFromAsset(mySpinnerSeat.getResources().getAssets(), "font/Roboto-Light.ttf");
        MyArrayAdapterSeat mas = new MyArrayAdapterSeat(rootView.getContext());
        mySpinnerSeat.setAdapter(mas);


        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    private class MyArrayAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
    public MyArrayAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

        @Override
        public int getCount(){
            return COUNTRIES.length;
        }
        @Override
        public Object getItem(int position){
            return position;
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ListContent holder;
            View v = convertView;
            if(v == null){
                v = mInflater.inflate(R.layout.fragmentaddorder_style,null);
                holder = new ListContent();

                holder.name = (TextView) v.findViewById(R.id.textView1);
                v.setTag(holder);

            } else {
                holder = (ListContent) v.getTag();
            }
            holder.name.setTypeface(myFont);
            holder.name.setTextSize(15);
            holder.name.setTextColor(getResources().getColor(R.color.black_light));

//            holder.name.setTypeface(null,Typeface.BOLD);

            holder.name.setText(""+COUNTRIES[position]);
            return v;
        }

    }

    static class ListContent {

        TextView name;

    }

    private class MyArrayAdapterSeat extends BaseAdapter {
        private LayoutInflater mInflater;
        public MyArrayAdapterSeat(Context context){
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount(){
            return SEATS.length;
        }
        @Override
        public Object getItem(int position){
            return position;
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ListContentSeat holder;
            View v = convertView;
            if(v == null){
                v = mInflater.inflate(R.layout.fragmentaddorder_styleseat,null);
                holder = new ListContentSeat();

                holder.name = (TextView) v.findViewById(R.id.textView2);
                v.setTag(holder);

            } else {
                holder = (ListContentSeat) v.getTag();
            }
            holder.name.setTypeface(myFont);
            holder.name.setTextSize(15);
            holder.name.setTextColor(getResources().getColor(R.color.black_light));
//            holder.name.setTypeface(null,Typeface.BOLD);

            holder.name.setText(""+SEATS[position]);
            return v;
        }

    }

    static class ListContentSeat {

        TextView name;

    }


}
