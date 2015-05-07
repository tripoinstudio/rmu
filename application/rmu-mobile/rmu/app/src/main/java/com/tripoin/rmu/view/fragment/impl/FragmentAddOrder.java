package com.tripoin.rmu.view.fragment.impl;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.synchronizer.impl.SynchronizeMaster;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.OrderTempModel;
import com.tripoin.rmu.model.persist.SeatModel;
import com.tripoin.rmu.model.persist.TrainModel;
import com.tripoin.rmu.persistence.orm_persistence.service.CarriageDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderTempDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.SeatDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.TrainDBManager;
import com.tripoin.rmu.rest.impl.CarriageListRest;
import com.tripoin.rmu.rest.impl.SeatListRest;
import com.tripoin.rmu.util.BluetoothUtils;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ISynchronizeMaster;
import com.tripoin.rmu.view.ui.CustomCardOrderTemp;
import com.tripoin.rmu.view.ui.PaddingHelper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Syahrial Fandrianah on 4/18/2015 : 1:41 AM.
 * mailto : sfandrianah2@gmail.com
 */
public class FragmentAddOrder extends Fragment implements ISynchronizeMaster {

    private String array_spinner_carriage[];
    private String array_spinner_seat[];
    private static final String[] COUNTRIES = new String[] { "Gerbong 1","Gerbong 2", "Gerbong 3", "Gerbong 4", "Gerbong 5" };
    private static final String[] SEATS = new String[] { "Seat 1","Seat 2", "Seat 3", "Seat 4", "Seat 5" };
    View rootView;
    View viewMenuList;
    private Typeface myFont;
    private Spinner mySpinner;
    private Typeface myFontSeat;
    private Spinner mySpinnerSeat;
    PropertyUtil propertyUtil;
    CarriageListRest carriageListRest;
    MyArrayAdapter ma;
    SeatListRest seatListRest;
    MyArrayAdapterSeat maSeat;
    private String today;
    private MasterASync masterASync;
    private Button bt_add_order;
    private Button bt_bayar;
    private TextView txus2;
    private Typeface faces2;
    private TextView txus;
    private Typeface faces;
    private TextView lbl1;
    private TextView lbl2;
    private TextView menuTotal;
    private BigDecimal totalOrder = new BigDecimal(0);
    /*private TextView menuName;
    private TextView menuPrice;
    private TextView menuTotal;*/
    private BluetoothUtils bluetoothUtils = new BluetoothUtils(FragmentAddOrder.this);
    private List<OrderTempModel> orderTempModelList = new ArrayList<OrderTempModel>();

    public FragmentAddOrder newInstance(String text){
        FragmentAddOrder mFragment = new FragmentAddOrder();
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_order, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        propertyUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(),rootView.getContext());
        OrderTempDBManager.init(rootView.getContext());
        orderTempModelList = OrderTempDBManager.getInstance().getAllData();
        if(orderTempModelList != null){
            for(OrderTempModel orderTempModel : orderTempModelList){
                totalOrder = new BigDecimal(orderTempModel.getPrice()).add(totalOrder);
            }
        }

        txus=(TextView)rootView.findViewById(R.id.tx_username);
        faces=Typeface.createFromAsset(txus.getResources().getAssets(),"font/Roboto-Light.ttf");
        txus.setText("Username      : ".concat(propertyUtil.getValuePropertyMap(PropertyConstant.USER_NAME.toString())));
        txus.setTypeface(faces);
        txus2=(TextView)rootView.findViewById(R.id.tx_dates);
        faces2=Typeface.createFromAsset(txus2.getResources().getAssets(),"font/Roboto-Light.ttf");
        today  = new SimpleDateFormat("EEEE, dd MMMMMM yyyy HH:mm:ss", new Locale("ID")).format(new Date());
        txus2.setText("Order Date   : ".concat(today));
        txus2.setTypeface(faces2);
        lbl1 = (TextView)rootView.findViewById(R.id.lbl_carriage);
        lbl1.setTypeface(Typeface.createFromAsset(lbl1.getResources().getAssets(),"font/Roboto-Light.ttf"));
        lbl2 = (TextView)rootView.findViewById(R.id.lbl_seat);
        lbl2.setTypeface(Typeface.createFromAsset(lbl2.getResources().getAssets(), "font/Roboto-Light.ttf"));
        /*menuName = (TextView)rootView.findViewById(R.id.lbl_menu_name);
        menuName.setTypeface(Typeface.createFromAsset(menuName.getResources().getAssets(),"font/Roboto-Light.ttf"));
        menuPrice = (TextView)rootView.findViewById(R.id.lbl_menu_price);
        menuPrice.setTypeface(Typeface.createFromAsset(menuPrice.getResources().getAssets(),"font/Roboto-Light.ttf"));*/
        menuTotal = (TextView)rootView.findViewById(R.id.lbl_menu_total);
        menuTotal.setTypeface(Typeface.createFromAsset(menuTotal.getResources().getAssets(),"font/Roboto-Light.ttf"));
        menuTotal.setText(String.valueOf(totalOrder).concat(ViewConstant.CURRENCY_PATTERN.toString()));

        OrderTempDBManager.init(rootView.getContext());
        insertDataTemporary();
        getAllOrderTempInTemporary();

        bt_bayar =(Button)rootView.findViewById(R.id.bt_bayar);
        bt_bayar.setTypeface(faces2);
        bt_bayar.setEnabled(true);
        bt_add_order = (Button) rootView.findViewById(R.id.btn_add_order);

        bt_add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentMenuList fragmentMenuList = new FragmentMenuList();
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentMenuList).commit();
            }
        });
        bt_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"0".equals(String.valueOf(totalOrder))){

                    String headerPrint = "\n\n\nPT. Reska Multi Usaha\n"
                            .concat("eRestorasi version 1.0\n")
                            .concat("Jln. Kapt Subidjanto\n")
                            .concat("Telp : 0212345678\n")
                            .concat("\n\n--------------------------------\n");

                    String orderNoPrintData = "Order No : ";
                    orderNoPrintData = orderNoPrintData.concat("OR0001")
                                                       .concat("\n--------------------------------\n");

                    String menuPrintData = "";
                    for(OrderTempModel orderTempModel : orderTempModelList){
                        int countMenuName = orderTempModel.getMenuName().length();
                        countMenuName = 12 - countMenuName;
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
                                .concat(ViewConstant.CURRENCY_PATTERN.toString()).concat("\n");
                    }

                    String totalOrderPadding = String.valueOf(totalOrder);
                    totalOrderPadding = new PaddingHelper().leftPaddingString(totalOrderPadding, 9, " ");

                    String totalPrintData = "--------------------------------\n"
                            .concat("Total          :  Rp.")
                            .concat(totalOrderPadding)
                            .concat(ViewConstant.CURRENCY_PATTERN.toString());

                    String footerPrint = "\n\n--------------------------------\n"
                            .concat("Thank You For Order\n")
                            .concat("Terima Kasih\n\n\n");

                    String print = headerPrint.concat(orderNoPrintData).concat(menuPrintData).concat(totalPrintData).concat(footerPrint);
                    Log.d("PRINT PAYMENT", print);
                    /*try {
                        bluetoothUtils.printData(print);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        });
        masterASync = new MasterASync();
        masterASync.execute();

//        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
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
            return array_spinner_carriage.length;
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
            holder.name.setTextSize(18);
            holder.name.setTextColor(getResources().getColor(R.color.black_light));
            //            holder.name.setTypeface(null,Typeface.BOLD);
            holder.name.setText(""+array_spinner_carriage[position]);
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
            return array_spinner_seat.length;
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
            holder.name.setTextSize(18);
            holder.name.setTextColor(getResources().getColor(R.color.black_light));

            holder.name.setText(""+array_spinner_seat[position]);
            return v;
        }

    }

    static class ListContentSeat {
        TextView name;

    }

    public void initSpinnerCarriage(List<CarriageModel> carriageModels) {
        int array =  carriageModels.size();
        array_spinner_carriage = new String[array];
        for(int i = 0; i<array;i++){
            array_spinner_carriage[i] = carriageModels.get(i).getCarriageNo();
        }
        mySpinner = (Spinner) rootView.findViewById(R.id.spinner_carriage);
        myFont = Typeface.createFromAsset(mySpinner.getResources().getAssets(), "font/Roboto-Light.ttf");
        ma = new MyArrayAdapter(rootView.getContext());
        mySpinner.setAdapter(ma);
    }

    public void initSpinnerSeat(List<SeatModel> seatModels) {
        int array = seatModels.size();
        array_spinner_seat = new String[array];
        for(int i = 0; i<array;i++){
            array_spinner_seat[i] = seatModels.get(i).getSeatNo();
        }

        mySpinnerSeat = (Spinner) rootView.findViewById(R.id.spinner_seat);
        myFont = Typeface.createFromAsset(mySpinner.getResources().getAssets(), "font/Roboto-Light.ttf");
        maSeat = new MyArrayAdapterSeat(rootView.getContext());
        mySpinnerSeat.setAdapter(maSeat);
    }

    @Override
    public void onPostFirstSyncMasterCarriage(List<CarriageModel> carriageModels) {
        initSpinnerCarriage(carriageModels);
    }

    @Override
    public void onPostContSyncMasterCarriage(List<CarriageModel> carriageModels) {
        initSpinnerCarriage(carriageModels);
    }

    @Override
    public void onPostFirstSyncMasterSeat(List<SeatModel> seatModels) {
        initSpinnerSeat(seatModels);
    }

    @Override
    public void onPostContSyncMasterSeat(List<SeatModel> seatModels) {
        initSpinnerSeat(seatModels);
    }

    @Override
    public void onPostFirstSyncMasterTrain(List<TrainModel> trainModels) {

    }

    @Override
    public void onPostContSyncMasterTrain(List<TrainModel> trainModels) {

    }

    private void insertDataTemporary(){
//        OrderTempModel orderTempModel = new OrderTempModel();
////        orderTempModel.setId(1);
//        orderTempModel.setMenuId("Nasi Basi");
//        orderTempModel.setQuantity("2");
//        orderTempModel.setPrice("2000000");
//
//        OrderTempDBManager.getInstance().insertEntity(orderTempModel);
    }

    private void getAllOrderTempInTemporary(){
        List<OrderTempModel> orderTempModelList = OrderTempDBManager.getInstance().getAllData();
        initCards(orderTempModelList);
    }

    private void initCards(List<OrderTempModel> orderTempModels){
        Log.d("ORDERTEMP_INITCARD_SIZE", String.valueOf(orderTempModels.size()));
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i<orderTempModels.size(); i++) {
            Card card = new CustomCardOrderTemp(getActivity(), R.layout.row_card_temp, orderTempModels.get(i));
            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        CardListView listView = (CardListView) rootView.findViewById(R.id.listOrderTemp);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    private class MasterASync extends AsyncTask {
        SynchronizeMaster synchronizeMaster;
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(rootView.getContext());
            progressDialog.setMessage("Loading menu list");
            progressDialog.setCancelable(false);
            progressDialog.show();
            CarriageDBManager.init(rootView.getContext());
            SeatDBManager.init(rootView.getContext());
            TrainDBManager.init(rootView.getContext());
            synchronizeMaster = new SynchronizeMaster(propertyUtil, rootView.getContext(), ModelConstant.REST_MASTER_TABLE.toString(), FragmentAddOrder.this);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            synchronizeMaster.detectVersionDiff();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
        }
    }

}
