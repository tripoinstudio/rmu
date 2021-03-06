package com.tripoin.rmu.view.fragment.impl;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.bluetooth.BluetoothEngine;
import com.tripoin.rmu.feature.scheduler.constant.IOrderStatusConstant;
import com.tripoin.rmu.feature.synchronizer.impl.SynchronizeOrderDetail;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailDTO;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailItemDTO;
import com.tripoin.rmu.model.DTO.order_header.OrderHeaderDTO;
import com.tripoin.rmu.model.DTO.order_header.OrderHeaderItemDTO;
import com.tripoin.rmu.model.DTO.print_message.PrintMessageDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.model.persist.OrderTempModel;
import com.tripoin.rmu.model.persist.VersionModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderDetailDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderListDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderTempDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.VersionDBManager;
import com.tripoin.rmu.rest.api.IOrderDetailPost;
import com.tripoin.rmu.rest.impl.OrderDetailListRest;
import com.tripoin.rmu.rest.impl.OrderHeaderListRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ISynchronizeOrderDetail;
import com.tripoin.rmu.view.fragment.base.ABaseNavigationDrawerFragment;
import com.tripoin.rmu.view.ui.CustomCardOrderDetail;
import com.tripoin.rmu.view.ui.CustomCardStatusOrderDetail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 5:38 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class FragmentOrderDetail extends ABaseNavigationDrawerFragment implements ISynchronizeOrderDetail, IOrderDetailPost{

    @InjectView(R.id.txtOrderId) TextView txtOrderListId;
    @InjectView(R.id.txtTrainCode) TextView txtTrainCode;
    @InjectView(R.id.txtCarriageCode)TextView txtCarriageCode;
    @InjectView(R.id.txtSeatCode) TextView txtSeatCode;
//    @InjectView(R.layout.order_status_card.R.id.txtOrderStatus)TextView txtOrderStatus;

    @InjectView(R.id.txtTotalPaid)TextView txtTotalPaid;
    @InjectView(R.id.btPrintOrder) Button btPrintOrder;
    @InjectView(R.id.listStatusOption) CardListView listView;
    @InjectView(R.id.listOrderDetailItem) CardListView listViewDetailOrderItem;

    private static String ORDER_LIST_ID = "ORDER_LIST_ID";
    private PropertyUtil securityUtil;
    private String orderListId;
    private List<OrderDetailModel> orderDetailModels;

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
        OrderDetailDBManager.init(getActivity());
        orderListId = getArguments().getString(ORDER_LIST_ID);
        try{
            orderDetailModels = OrderDetailDBManager.getInstance().getListDataFromQuery(ModelConstant.ORDER_DETAIL_ORDER_HEADER_NO, orderListId);
            if(orderDetailModels.size() > 0 || orderDetailModels != null){
                onPostSyncOrderDetail(orderDetailModels);
            }else{
                new OrderDetailListRest(this) {
                    @Override
                    public String getOrderHeaderId() {
                        return orderListId;
                    }

                    @Override
                    public Context getContext() {
                        return getActivity();
                    }
                }.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
            }
        }catch (Exception e){
            new OrderDetailListRest(this) {
                @Override
                public String getOrderHeaderId() {
                    return orderListId;
                }

                @Override
                public Context getContext() {
                    return getActivity();
                }
            }.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
            e.printStackTrace();
        }
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
        try{
            List<OrderDetailModel> orderDetailStatusModels = new ArrayList<OrderDetailModel>();
            if(Integer.parseInt(detailModels.get(0).getOrderHeaderStatus()) < IOrderStatusConstant.DONE ||
                    detailModels.get(0).getOrderHeaderStatus().equals(String.valueOf(IOrderStatusConstant.RETRY))){
                btPrintOrder.setVisibility(View.VISIBLE);
            }
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
            initStatusCards(orderDetailStatusModels, detailModels);
            initDetailCards(detailModels);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPostSynchronize(Object objectResult) {
        /*Order Detail Post*/
        if(objectResult != null){
            OrderDetailDTO orderDetailDTO = (OrderDetailDTO) objectResult;
            List<OrderDetailModel> detailModels = new ArrayList<OrderDetailModel>();
            for(OrderDetailItemDTO orderDetailItemDTO: orderDetailDTO.getOrderDetailItemDTOs()){
                OrderDetailModel detailModel = new OrderDetailModel();
                detailModel.setOrderHeaderNo(orderDetailItemDTO.getOrderHeaderNo());
                detailModel.setOrderDetailTotalOrder(orderDetailItemDTO.getOrderDetailTotalOrder());
                detailModel.setOrderDetailTotalAmount(orderDetailItemDTO.getOrderDetailTotalAmount());
                detailModel.setOrderHeaderStatus(orderDetailItemDTO.getOrderHeaderStatus());
                detailModel.setMenuCode(orderDetailItemDTO.getMenuCode());
                detailModel.setMenuName(orderDetailItemDTO.getMenuName());
                detailModel.setSeatCode(orderDetailItemDTO.getSeatCode());
                detailModel.setCarriageCode(orderDetailItemDTO.getCarriageCode());
                detailModel.setTrainCode(orderDetailItemDTO.getTrainCode());
                detailModels.add(detailModel);
                OrderDetailDBManager.getInstance().insertEntity(detailModel);
            }
            onPostSyncOrderDetail(detailModels);
        }else{
            Log.d("Sync Orderlist Object Result", "not found");
        }
    }

    private void initStatusCards(List<OrderDetailModel> orderDetailModels, List<OrderDetailModel> detailModels){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i<orderDetailModels.size(); i++) {
            Card card = new CustomCardStatusOrderDetail(getActivity(), R.layout.order_status_card, orderDetailModels.get(i), detailModels);
            card.setBackgroundResource(getResources().getDrawable(R.drawable.textlinesfullborder));
            cards.add(card);
        }
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(rootView.getContext(), cards);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    private void initDetailCards(List<OrderDetailModel> orderDetailModels){
        txtTrainCode.setText(orderDetailModels.get(0).getTrainCode());
        txtCarriageCode.setText(orderDetailModels.get(0).getCarriageCode());
        txtSeatCode.setText(orderDetailModels.get(0).getSeatCode());
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

    @OnClick(R.id.btPrintOrder)
    public void printOrder(){
        BluetoothEngine bluetoothEngine = new BluetoothEngine(getActivity());
        try{
            String orderNo = txtOrderListId.getText().toString();
            PrintMessageDTO printMessageDTO = new PrintMessageDTO();
            OrderTempDBManager.init(rootView.getContext());
            List<OrderDetailModel> orderDetailModelList = OrderDetailDBManager.getInstance().getListDataFromQuery(ModelConstant.ORDER_DETAIL_ORDER_HEADER_NO.toString(), orderNo);
            List<OrderTempModel> orderTempModelList = new ArrayList<OrderTempModel>();
            BigDecimal totalOrder = new BigDecimal(0);
            for(OrderDetailModel orderDetailModel : orderDetailModelList){
                OrderTempModel orderTempModel = new OrderTempModel();
                orderTempModel.setOrderNo(orderDetailModel.getOrderHeaderNo());
                orderTempModel.setPrice(orderDetailModel.getOrderDetailTotalAmount());
                orderTempModel.setQuantity(orderDetailModel.getOrderDetailTotalOrder());
                orderTempModel.setMenuCode(orderDetailModel.getMenuCode());
                orderTempModel.setMenuName(orderDetailModel.getMenuName());
                orderTempModel.setSeatCode(orderDetailModel.getSeatCode());
                orderTempModel.setCarriageCode(orderDetailModel.getCarriageCode());
                orderTempModel.setTrainCode(orderDetailModel.getTrainCode());
                orderTempModelList.add(orderTempModel);
                totalOrder = totalOrder.add(new BigDecimal(orderDetailModel.getOrderDetailTotalAmount()));
            }
            printMessageDTO.setOrderNo(orderNo);
            printMessageDTO.setTotal(String.valueOf(totalOrder));
            printMessageDTO.setMessageItemDTOs(orderTempModelList);
            Log.d("PRINT",printMessageDTO.toString());
            if(bluetoothEngine.checkOnBluetooth()){
                if(bluetoothEngine.openBluetoothConnection()) {
                    bluetoothEngine.printTemplateString(bluetoothEngine.templateMessageDto(printMessageDTO));
                }else{
                    Toast.makeText(rootView.getContext(),"Error Bluetooth Connection!",Toast.LENGTH_SHORT).show();
                }
            }else{
                bluetoothEngine.activeBluetooth();
            }
        }catch (Exception e){
            Toast.makeText(rootView.getContext(), e.getMessage().concat("A error occurred Bluetooth isn't available!"), Toast.LENGTH_SHORT).show();
        }
    }

}
