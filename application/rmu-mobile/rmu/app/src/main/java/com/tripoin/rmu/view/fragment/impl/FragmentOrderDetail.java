package com.tripoin.rmu.view.fragment.impl;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.synchronizer.impl.SynchronizeOrderDetail;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderDetailDBManager;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderDetail;
import com.tripoin.rmu.view.ui.CustomCardOrderDetail;
import com.tripoin.rmu.view.ui.CustomCardStatusOrderDetail;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 5:38 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentOrderDetail extends Fragment implements ISynchronizeOrderDetail{

    private View rootView;
    private static String ORDER_LIST_ID = "ORDER_LIST_ID";
    private PropertyUtil securityUtil;
    private String orderListId;
    private TextView txtTrainCode;
    private TextView txtCarriageCode;
    private TextView txtSeatCode;
    private TextView txtTotalPaid;

    public FragmentOrderDetail newInstance(String orderListId){
        FragmentOrderDetail mFragment = new FragmentOrderDetail();
        Bundle bundle = new Bundle();
        bundle.putString(ORDER_LIST_ID, orderListId);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FragmentOrderDetail mFragment = new FragmentOrderDetail();
        Bundle bundle = new Bundle();
        bundle.putString(ORDER_LIST_ID, "");
        mFragment.setArguments(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail_order_list, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), rootView.getContext());
        new OrderDetailAsync().execute();
        orderListId = getArguments().getString(ORDER_LIST_ID);
        Log.d("ORDERLISTID", orderListId);
        TextView txtOrderListId = (TextView) rootView.findViewById(R.id.txtOrderId);
        txtTrainCode = (TextView) rootView.findViewById(R.id.txtTrainCode);
        txtCarriageCode = (TextView) rootView.findViewById(R.id.txtCarriageCode);
        txtSeatCode = (TextView) rootView.findViewById(R.id.txtSeatCode);
        txtTotalPaid = rootView
        txtOrderListId.setText(orderListId);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onPostFirstSyncOrderDetail(OrderDetailModel orderDetailModel, List<OrderDetailModel> orderDetailModels2) {
        for( OrderDetailModel detailModel: orderDetailModels2){
            Log.d("frag order derak model", detailModel.toString());
        }
        List<OrderDetailModel> orderDetailModels = new ArrayList<OrderDetailModel>();
        Log.d("CURRENTSTATUS first", orderDetailModel.getOrderHeaderStatus());
        int[] headerId = getProcessStatus(Integer.parseInt(orderDetailModel.getOrderHeaderStatus()));
        for (int i = 0; i < headerId.length; i++) {
            if (headerId[i] != 0) {
                OrderDetailModel detailModel = new OrderDetailModel();
                detailModel.setOrderHeaderNo(orderDetailModel.getOrderHeaderNo());
                detailModel.setOrderHeaderStatus(String.valueOf(headerId[i]));
                orderDetailModels.add(detailModel);
            }
        }
        initCards(orderDetailModels);
        initDetailCards(orderDetailModels2);
    }

        @Override
        public void onPostContSyncOrderDetail(OrderDetailModel orderDetailModel, List<OrderDetailModel> orderDetailModels2){
            List<OrderDetailModel> orderDetailModels = new ArrayList<OrderDetailModel>();
            Log.d("CURRENTSTATUS cont", orderDetailModel.getOrderHeaderStatus());
            int[] headerId = getProcessStatus(Integer.parseInt(orderDetailModel.getOrderHeaderStatus()));
            for (int i = 0; i < headerId.length; i++) {
                if (headerId[i] != 0) {
                    OrderDetailModel detailModel = new OrderDetailModel();
                    detailModel.setOrderHeaderNo(orderDetailModel.getOrderHeaderNo());
                    detailModel.setOrderHeaderStatus(String.valueOf(headerId[i]));
                    orderDetailModels.add(detailModel);
                }
            }
            initCards(orderDetailModels);
            initDetailCards(orderDetailModels2);
        }


    private class OrderDetailAsync extends AsyncTask{

        private ProgressDialog progressDialog;
        private SynchronizeOrderDetail synchronizeOrderDetail;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            OrderDetailDBManager.init(rootView.getContext());
            progressDialog = new ProgressDialog(rootView.getContext());
            progressDialog.setMessage("Loading order detail");
            progressDialog.setCancelable(false);
            progressDialog.show();
            synchronizeOrderDetail = new SynchronizeOrderDetail(securityUtil, rootView.getContext(), ModelConstant.REST_ORDER_DETAIL_TABLE.toString(), FragmentOrderDetail.this) {
                @Override
                public String getOrderHeader() {
                    return orderListId;
                }
            };
        }

        @Override
        protected Object doInBackground(Object[] params) {
            synchronizeOrderDetail.detectVersionDiff();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressDialog.dismiss();
        }
    }


    private void initCards(List<OrderDetailModel> orderDetailModels){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i<orderDetailModels.size(); i++) {
            Card card = new CustomCardStatusOrderDetail(getActivity(), R.layout.order_status_card, orderDetailModels.get(i));
            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);

        CardListView listView = (CardListView) rootView.findViewById(R.id.listStatusOption);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    private void initDetailCards(List<OrderDetailModel> orderDetailModels){
        txtTrainCode.setText(ViewConstant.TRAIN_NO.toString().concat(orderDetailModels.get(0).getTrainCode()));
        txtCarriageCode.setText(ViewConstant.CARRIAGE_NO.toString().concat(orderDetailModels.get(0).getCarriageCode()));
        txtSeatCode.setText(ViewConstant.SEAT_NO.toString().concat(orderDetailModels.get(0).getSeatCode()));
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i<orderDetailModels.size(); i++) {
            Card card = new CustomCardOrderDetail(getActivity(), R.layout.order_detail_card, orderDetailModels.get(i));
            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);

        CardListView listView = (CardListView) rootView.findViewById(R.id.listOrderDetailItem);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }


    public int[] getProcessStatus(int currentStatus){
      int [] result = new int[5];
      for(int a=1; a<5; a++){
          if(a!=currentStatus){
              result[a] = a;
          }else{
              continue;
          }
      }
      return result;
    };

}
