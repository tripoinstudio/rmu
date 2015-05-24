package com.tripoin.rmu.view.fragment.impl;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.bluetooth.BluetoothEngine;
import com.tripoin.rmu.feature.scheduler.constant.IOrderStatusConstant;
import com.tripoin.rmu.feature.synchronizer.impl.SynchronizeMaster;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailDTO;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailItemDTO;
import com.tripoin.rmu.model.DTO.print_message.PrintMessageDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.CarriageModel;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.model.persist.OrderTempModel;
import com.tripoin.rmu.model.persist.SeatModel;
import com.tripoin.rmu.model.persist.TrainModel;
import com.tripoin.rmu.persistence.orm_persistence.service.CarriageDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderDetailDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderListDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderTempDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.SeatDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.TrainDBManager;
import com.tripoin.rmu.rest.api.IPaymentPost;
import com.tripoin.rmu.rest.impl.PaymentRest;
import com.tripoin.rmu.util.NetworkConnectivity;
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
public class FragmentAddOrder extends Fragment implements ISynchronizeMaster, IPaymentPost {

    private String array_spinner_carriage[];
    private String arraySpinnerCarriageCode[];
    private String array_spinner_seat[];
    private String arraySpinnerSeatCode[];
    private View rootView;
    private Typeface myFont;
    private Spinner mySpinner;
    private Spinner mySpinnerSeat;
    private PropertyUtil propertyUtil;
    private MyArrayAdapter ma;
    private MyArrayAdapterSeat maSeat;
    private String today;
    private MasterASync masterASync;
    private Button bt_add_order;
    private Button bt_bayar;
    private Button bt_bayar_direct;
    private Button bt_cancel;
    private TextView txus2;
    private Typeface faces2;
    private TextView txus;
    private Typeface faces;
    private TextView lbl1;
    private TextView lbl2;
    private TextView menuTotal;
    private BigDecimal totalOrder = new BigDecimal(0);
    private BluetoothEngine bluetoothEngine;
    private List<OrderTempModel> orderTempModelList = new ArrayList<OrderTempModel>();
    private List<TrainModel> trainModels = new ArrayList<TrainModel>();
    private String waitingStatus = String.valueOf(IOrderStatusConstant.NEW);

    public FragmentAddOrder newInstance(){
        return new FragmentAddOrder();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_order, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        getActivity().setTitle(ViewConstant.FRAGMENT_ADD_ORDER_TITLE.toString());
        propertyUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(),rootView.getContext());
        OrderTempDBManager.init(rootView.getContext());
        orderTempModelList = OrderTempDBManager.getInstance().getAllData();
        if(orderTempModelList != null){
            for(OrderTempModel orderTempModel : orderTempModelList){
                totalOrder = new BigDecimal(orderTempModel.getPrice()).add(totalOrder);
            }
        }

        bluetoothEngine = new BluetoothEngine(getActivity());

        txus=(TextView)rootView.findViewById(R.id.tx_username);
        faces=Typeface.createFromAsset(txus.getResources().getAssets(),"font/Roboto-Light.ttf");
        txus.setText(propertyUtil.getValuePropertyMap(PropertyConstant.USER_NAME.toString()));
        txus.setTypeface(faces);
        txus2=(TextView)rootView.findViewById(R.id.tx_dates);
        faces2=Typeface.createFromAsset(txus2.getResources().getAssets(),"font/Roboto-Light.ttf");
        today  = new SimpleDateFormat("EEEE, dd MMMMMM yyyy HH:mm:ss", new Locale("ID")).format(new Date());
        txus2.setText(today);
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
        menuTotal.setText(ViewConstant.CURRENCY_IDR.toString().concat(String.valueOf(totalOrder)).concat(ViewConstant.CURRENCY_PATTERN.toString()));

        OrderTempDBManager.init(rootView.getContext());
        getAllOrderTempInTemporary();

        bt_bayar =(Button)rootView.findViewById(R.id.bt_bayar);
        bt_bayar_direct =(Button)rootView.findViewById(R.id.bt_bayar_direct);
        bt_bayar.setEnabled(true);
        bt_add_order = (Button) rootView.findViewById(R.id.btn_add_order);

        bt_add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentMenuList fragmentMenuList = new FragmentMenuList();
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentMenuList).addToBackStack(null).commit();

            }
        });

        bt_cancel =(Button)rootView.findViewById(R.id.bt_cancel);
        bt_cancel.setEnabled(true);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDelete = new AlertDialog.Builder(getActivity());
                alertDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OrderTempDBManager.getInstance().executeRaw("DELETE FROM ".concat(ModelConstant.ORDER_TEMP_TABLE));
                        FragmentOrderList fragmentOrderList = new FragmentOrderList();
                        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                        mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderList).commit();
                    }
                });
                alertDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDelete.create();
                alertDialog.setTitle("Are you sure?");
                alertDialog.show();
            }
        });

        bt_bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"0".equals(String.valueOf(totalOrder))) {
                    NetworkConnectivity networkConnectivity = new NetworkConnectivity(getActivity());
                    waitingStatus = String.valueOf(IOrderStatusConstant.NEW);
                    if(networkConnectivity.checkConnectivity()) {
                        String orderNo = "";
                        if (orderTempModelList != null) {
                            orderNo = orderTempModelList.get(0).getOrderNo();
                        }
                        if (orderNo == null || orderNo == "")
                            transactionOrder(String.valueOf(IOrderStatusConstant.NEW));
                        else
                            printPayment(orderNo);
                    }else{
                        saveToLocal();
                    }
                    FragmentOrderList fragmentOrderList = new FragmentOrderList();
                    FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                    mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderList).commit();
                }
            }
        });

        bt_bayar_direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"0".equals(String.valueOf(totalOrder))) {
                    NetworkConnectivity networkConnectivity = new NetworkConnectivity(getActivity());
                    waitingStatus = String.valueOf(IOrderStatusConstant.DONE);
                    if(networkConnectivity.checkConnectivity()) {
                        String orderNo = "";
                        if (orderTempModelList != null) {
                            orderNo = orderTempModelList.get(0).getOrderNo();
                        }
                        if (orderNo == null || orderNo == "")
                            transactionOrder(String.valueOf(IOrderStatusConstant.DONE));
                        else
                            printPayment(orderNo);
                    }else{
                        saveToLocal();
                    }
                    FragmentOrderList fragmentOrderList = new FragmentOrderList();
                    FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                    mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderList).commit();
                }
            }
        });

        masterASync = new MasterASync();
        masterASync.execute();
        return rootView;
    }

    public void transactionOrder(String statusOrder){
        PaymentRest paymentRest = new PaymentRest(FragmentAddOrder.this, statusOrder) {
            @Override
            public String getPaymentData() {
                String trainCode = trainModels.get(0).getTrainCode();
                String carriageCode = arraySpinnerCarriageCode[((int) mySpinner.getSelectedItemId())];
                String seatCode = arraySpinnerSeatCode[((int) mySpinnerSeat.getSelectedItemId())];
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                ArrayList<OrderDetailItemDTO> orderDetailItemDTOList = new ArrayList<OrderDetailItemDTO>();
                for (OrderTempModel orderTempModel : orderTempModelList) {
                    OrderDetailItemDTO orderDetailItemDTO = new OrderDetailItemDTO();
                    orderDetailItemDTO.setMenuName(orderTempModel.getMenuName());
                    orderDetailItemDTO.setMenuCode(orderTempModel.getMenuCode());
                    orderDetailItemDTO.setOrderDetailTotalAmount(orderTempModel.getPrice());
                    orderDetailItemDTO.setOrderDetailTotalOrder(orderTempModel.getQuantity());
                    orderDetailItemDTO.setTrainCode(trainCode);
                    orderDetailItemDTO.setCarriageCode(carriageCode);
                    orderDetailItemDTO.setSeatCode(seatCode);
                    orderDetailItemDTO.setOrderHeaderStatus(getStatusOrder());
                    orderDetailItemDTOList.add(orderDetailItemDTO);
                }
                orderDetailDTO.setOrderDetailItemDTOs(orderDetailItemDTOList);
                ObjectMapper om = new ObjectMapper();
                String jsonData = null;
                try {
                    jsonData = om.writeValueAsString(orderDetailDTO);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return jsonData;
            }

            @Override
            public Context getContext() {
                return rootView.getContext();
            }
        };
        paymentRest.execute(propertyUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
    }

    private void saveToLocal(){
        try{
            OrderListDBManager.init(rootView.getContext());
            OrderDetailDBManager.init(rootView.getContext());
            String orderHeaderNo = OrderListDBManager.getInstance().getOrderDataFromQuery(ModelConstant.ORDER_LIST_ORDER_TIME,false).getOrderId();
            String lastOrderHeaderNo = orderHeaderNo.substring(10);
            int idHeader = Integer.parseInt(lastOrderHeaderNo)+1;
            String idOrderHeaderNo = new PaddingHelper().leftPaddingString(String.valueOf(idHeader),5,"0");
            String dateOrderHeaderNo = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String tempOrderHeaderNo = "TO".concat(dateOrderHeaderNo).concat(idOrderHeaderNo);
            OrderListModel orderListModel = new OrderListModel();
            orderListModel.setOrderId(tempOrderHeaderNo);
            orderListModel.setTotalPaid(String.valueOf(totalOrder));
            orderListModel.setProcessStatus(IOrderStatusConstant.PENDING);
            orderListModel.setWaitingStatus(Integer.parseInt(waitingStatus));
            orderListModel.setOrderTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.0").format(new Date()));
            orderListModel.setSeatNumber(array_spinner_seat[((int) mySpinnerSeat.getSelectedItemId())]);
            orderListModel.setCarriageNumber(array_spinner_carriage[((int) mySpinner.getSelectedItemId())]);
            OrderListDBManager.getInstance().insertEntity(orderListModel);

            for (OrderTempModel orderTempModel : orderTempModelList) {
                OrderDetailModel orderDetail = new OrderDetailModel();
                orderDetail.setOrderHeaderNo(tempOrderHeaderNo);
                orderDetail.setOrderHeaderStatus(String.valueOf(IOrderStatusConstant.PENDING));
                orderDetail.setOrderHeaderStatusWaiting(waitingStatus);
                orderDetail.setMenuCode(orderTempModel.getMenuCode());
                orderDetail.setMenuName(orderTempModel.getMenuName());
                orderDetail.setOrderDetailTotalOrder(orderTempModel.getQuantity());
                orderDetail.setOrderDetailTotalAmount(orderTempModel.getPrice());
                orderDetail.setSeatCode(arraySpinnerSeatCode[((int) mySpinnerSeat.getSelectedItemId())]);
                orderDetail.setCarriageCode(arraySpinnerCarriageCode[((int) mySpinner.getSelectedItemId())]);
                orderDetail.setTrainCode(trainModels.get(0).getTrainCode());
                OrderDetailDBManager.getInstance().insertEntity(orderDetail);
            }
            OrderTempDBManager.getInstance().executeRaw("DELETE FROM ".concat(ModelConstant.ORDER_TEMP_TABLE));
        }catch (Exception e){

        }finally {
            Toast.makeText(rootView.getContext(), "Order Pending. Please check your connection!", Toast.LENGTH_LONG).show();
        }
    }

    private void printPayment(String orderNo){
        PrintMessageDTO printMessageDTO = new PrintMessageDTO();
        printMessageDTO.setOrderNo(orderNo);
        printMessageDTO.setTotal(String.valueOf(totalOrder));
        printMessageDTO.setMessageItemDTOs(orderTempModelList);
        try{
            if(bluetoothEngine.checkOnBluetooth()){
                if(bluetoothEngine.openBluetoothConnection()) {
                    bluetoothEngine.printTemplateString(bluetoothEngine.templateMessageDto(printMessageDTO));
                }else{
                    Toast.makeText(rootView.getContext(),"Error Bluetooth Connection!",Toast.LENGTH_SHORT).show();
                }
            }else{
                /*Toast.makeText(rootView.getContext(),"Please Active Bluetooth!",Toast.LENGTH_SHORT).show();*/
                bluetoothEngine.activeBluetooth();
            }
        }catch (Exception e){
            Toast.makeText(rootView.getContext(),e.getMessage().concat("A error occurred Bluetooth isn't available!"),Toast.LENGTH_SHORT).show();
        }finally {
            OrderTempDBManager.getInstance().executeRaw("DELETE FROM ".concat(ModelConstant.ORDER_TEMP_TABLE));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onPostSyncPayment(Object objectResult) {
        if(objectResult != null){
            OrderDetailDTO orderDetailDTO = (OrderDetailDTO)objectResult;
            if(orderDetailDTO.getErr_code().equals(ViewConstant.ZERO.toString())) {
                String orderNo = "";
                for (OrderDetailItemDTO orderDetailItemDTO : orderDetailDTO.getOrderDetailItemDTOs()) {
                    orderNo = orderDetailItemDTO.getOrderHeaderNo();
                }
                if (orderTempModelList != null) {
                    for (OrderTempModel orderTempModel : orderTempModelList) {
                        orderTempModel.setOrderNo(orderNo);
                        OrderTempDBManager.getInstance().updateEntity(orderTempModel);
                    }
                }
                printPayment(orderNo);
            }else{
                saveToLocal();
            }
        }else{
            saveToLocal();
        }
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
        arraySpinnerCarriageCode = new String[array];
        for(int i = 0; i<array;i++){
            array_spinner_carriage[i] = carriageModels.get(i).getCarriageNo();
            arraySpinnerCarriageCode[i] = carriageModels.get(i).getCarriageCode();
        }
        mySpinner = (Spinner) rootView.findViewById(R.id.spinner_carriage);
        myFont = Typeface.createFromAsset(mySpinner.getResources().getAssets(), "font/Roboto-Light.ttf");
        ma = new MyArrayAdapter(rootView.getContext());
        mySpinner.setAdapter(ma);
    }

    public void initSpinnerSeat(List<SeatModel> seatModels) {
        int array = seatModels.size();
        array_spinner_seat = new String[array];
        arraySpinnerSeatCode = new String[array];
        for(int i = 0; i<array;i++){
            array_spinner_seat[i] = seatModels.get(i).getSeatNo();
            arraySpinnerSeatCode[i] = seatModels.get(i).getSeatCode();
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
        this.trainModels = trainModels;
    }

    @Override
    public void onPostContSyncMasterTrain(List<TrainModel> trainModels) {
        this.trainModels = trainModels;
    }

    public void getAllOrderTempInTemporary(){
        List<OrderTempModel> orderTempModelList = OrderTempDBManager.getInstance().getAllData();
        initCards(orderTempModelList);
    }

    private void initCards(List<OrderTempModel> orderTempModels){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i<orderTempModels.size(); i++) {
            Card card = new CustomCardOrderTemp(getActivity(), R.layout.row_card_temp, orderTempModels.get(i));
            card.setSwipeable(true);
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
            synchronizeMaster = new SynchronizeMaster(propertyUtil, rootView.getContext(), ModelConstant.REST_MASTER_TABLE, FragmentAddOrder.this);
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
