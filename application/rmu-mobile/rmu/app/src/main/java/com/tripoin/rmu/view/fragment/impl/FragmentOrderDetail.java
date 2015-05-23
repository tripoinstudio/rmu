package com.tripoin.rmu.view.fragment.impl;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.scheduler.constant.IOrderStatusConstant;
import com.tripoin.rmu.feature.synchronizer.impl.SynchronizeOrderDetail;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderDetailDBManager;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderDetail;
import com.tripoin.rmu.view.fragment.base.ABaseNavigationDrawerFragment;
import com.tripoin.rmu.view.ui.CustomCardOrderDetail;
import com.tripoin.rmu.view.ui.CustomCardStatusOrderDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 5:38 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class FragmentOrderDetail extends ABaseNavigationDrawerFragment implements ISynchronizeOrderDetail{

    @InjectView(R.id.txtOrderId) TextView txtOrderListId;
    @InjectView(R.id.txtTrainCode) TextView txtTrainCode;
    @InjectView(R.id.txtCarriageCode)TextView txtCarriageCode;
    @InjectView(R.id.txtSeatCode) TextView txtSeatCode;
//    @InjectView(R.layout.order_status_card.R.id.txtOrderStatus)TextView txtOrderStatus;

    @InjectView(R.id.txtTotalPaid)TextView txtTotalPaid;
    @InjectView(R.id.listStatusOption) CardListView listView;
    @InjectView(R.id.listOrderDetailItem) CardListView listViewDetailOrderItem;

    private static String ORDER_LIST_ID = "ORDER_LIST_ID";
    private PropertyUtil securityUtil;
    private String orderListId;

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
        bundle.putString(ORDER_LIST_ID, ViewConstant.EMPTY.toString());
        mFragment.setArguments(bundle);
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_detail_order_list;
    }

    @Override
    public String getFragmentTitle() {
        return ViewConstant.FRAGMENT_ORDER_DETAIL_TITLE.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override

    public void initWidget() {
        securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), getActivity());
        new OrderDetailAsync().execute();

        orderListId = getArguments().getString(ORDER_LIST_ID);
        txtOrderListId.setText(orderListId);
    }

    @Override
    public List<TextView> getTextViews() {
        textViews = new ArrayList<TextView>();
        textViews.add(txtOrderListId);
        textViews.add(txtTrainCode);
        textViews.add(txtSeatCode);
        textViews.add(txtCarriageCode);
        textViews.add(txtTotalPaid);
        return super.getTextViews();
    }

    @Override
    public void onPostSyncOrderDetail(List<OrderDetailModel> detailModels) {
        List<OrderDetailModel> orderDetailStatusModels = new ArrayList<OrderDetailModel>();
        for (int i = IOrderStatusConstant.CANCEL ; i <= IOrderStatusConstant.PENDING ; i++) {
            int headerStatus = Integer.parseInt(detailModels.get(0).getOrderHeaderStatus());
            OrderDetailModel detailModel;
            if(headerStatus == IOrderStatusConstant.PREPARED) {
                for (int a = IOrderStatusConstant.DONE; a <= IOrderStatusConstant.PENDING; a++) {
                    detailModel = new OrderDetailModel();
                    detailModel.setOrderHeaderNo(detailModels.get(0).getOrderHeaderNo());
                    detailModel.setOrderHeaderStatus(String.valueOf(a));
                    orderDetailStatusModels.add(detailModel);
                }
                break;
            }else if(headerStatus == IOrderStatusConstant.CANCEL){
                detailModel = new OrderDetailModel();
                detailModel.setOrderHeaderNo(detailModels.get(0).getOrderHeaderNo());
                detailModel.setOrderHeaderStatus(String.valueOf(IOrderStatusConstant.CANCEL));
                orderDetailStatusModels.add(detailModel);
                break;
            }else if ( headerStatus == IOrderStatusConstant.PENDING ) {
                for(int a=IOrderStatusConstant.CANCEL; a<=IOrderStatusConstant.RETRY; a+=2){
                    detailModel = new OrderDetailModel();
                    detailModel.setOrderHeaderNo(detailModels.get(0).getOrderHeaderNo());
                    detailModel.setOrderHeaderStatus(String.valueOf(a));
                    orderDetailStatusModels.add(detailModel);
                }
                break;
            }else{
                detailModel = new OrderDetailModel();
                detailModel.setOrderHeaderNo(detailModels.get(0).getOrderHeaderNo());
                detailModel.setOrderHeaderStatus(String.valueOf(i));
                orderDetailStatusModels.add(detailModel);
            }
        }
        initStatusCards(orderDetailStatusModels);
        initDetailCards(detailModels);
    }


    private class OrderDetailAsync extends AsyncTask{

        private ProgressDialog progressDialog;
        private SynchronizeOrderDetail synchronizeOrderDetail;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            OrderDetailDBManager.init(getActivity());
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading order detail");
            progressDialog.setCancelable(false);
            progressDialog.show();
            synchronizeOrderDetail = new SynchronizeOrderDetail(securityUtil, getActivity(), ModelConstant.REST_ORDER_DETAIL_TABLE, FragmentOrderDetail.this) {
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

    private void initStatusCards(List<OrderDetailModel> orderDetailModels){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i<orderDetailModels.size(); i++) {
            Card card = new CustomCardStatusOrderDetail(getActivity(), R.layout.order_status_card, orderDetailModels.get(i));
            card.setBackgroundResource(getResources().getDrawable(R.drawable.textlinesfullborder));
            cards.add(card);
        }
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    private void initDetailCards(List<OrderDetailModel> orderDetailModels){
        txtTrainCode.setText(ViewConstant.TRAIN_NO.toString().concat(orderDetailModels.get(0).getTrainCode()));
        txtCarriageCode.setText(ViewConstant.CARRIAGE_NO.toString().concat(orderDetailModels.get(0).getCarriageCode()));
        txtSeatCode.setText(ViewConstant.SEAT_NO.toString().concat(orderDetailModels.get(0).getSeatCode()));
        ArrayList<Card> cards = new ArrayList<Card>();
        int totalPaid = 0;
        for (int i = 0; i<orderDetailModels.size(); i++) {
            Card card = new CustomCardOrderDetail(getActivity(), R.layout.order_detail_card, orderDetailModels.get(i));
            totalPaid += Integer.parseInt(orderDetailModels.get(i).getOrderDetailTotalAmount());
            card.setBackgroundResource(getResources().getDrawable(R.drawable.textlines));
            cards.add(card);
        }
        txtTotalPaid.setText(ViewConstant.TOTAL_PAID.toString().concat(ViewConstant.IDR.toString()).concat(ViewConstant.SPACE.toString()).concat(String.valueOf(totalPaid)));

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);

        if (listViewDetailOrderItem != null) {
            listViewDetailOrderItem.setAdapter(mCardArrayAdapter);
        }
    }


}
