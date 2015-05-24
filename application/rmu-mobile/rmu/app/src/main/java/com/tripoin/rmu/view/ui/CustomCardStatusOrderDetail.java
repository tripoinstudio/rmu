package com.tripoin.rmu.view.ui;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.Picasso;
import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.bluetooth.BluetoothEngine;
import com.tripoin.rmu.feature.scheduler.constant.IOrderStatusConstant;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailDTO;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailItemDTO;
import com.tripoin.rmu.model.DTO.order_header.OrderHeaderDTO;
import com.tripoin.rmu.model.DTO.print_message.PrintMessageDTO;
import com.tripoin.rmu.model.api.ModelConstant;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.model.persist.OrderTempModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderDetailDBManager;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderListDBManager;
import com.tripoin.rmu.rest.api.IPaymentPost;
import com.tripoin.rmu.rest.api.IUpdateOrderStatusPost;
import com.tripoin.rmu.rest.impl.PaymentRest;
import com.tripoin.rmu.rest.impl.UpdateOrderStatusRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ICustomCardStatus;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class CustomCardStatusOrderDetail extends Card implements IUpdateOrderStatusPost, IPaymentPost {

    private OrderDetailModel orderDetailModel;
    private FragmentActivity activity;
    private ICustomCardStatus iCustomCardStatus;
    private  List<OrderDetailModel> orderDetailModelList;

    public CustomCardStatusOrderDetail(FragmentActivity context, int innerLayout, OrderDetailModel orderDetailModel,  List<OrderDetailModel> orderDetailModelList) {
        super(context, innerLayout);
        this.activity = context;
        this.orderDetailModel = orderDetailModel;
        iCustomCardStatus = new CustomCardStatusImpl(context);
        this.orderDetailModelList = orderDetailModelList;
        init();
    }

    private void init(){
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                UpdateOrderStatusRest updateOrderStatusRest = new UpdateOrderStatusRest(CustomCardStatusOrderDetail.this) {
                    @Override
                    public String getOrderHeaderId() {
                        return orderDetailModel.getOrderHeaderNo();
                    }
                    @Override
                    public String getStatusTarget() {
                        return orderDetailModel.getOrderHeaderStatus();
                    }
                    @Override
                    public Context getContext() {
                        return activity;
                    }
                };

                PaymentRest paymentRest = new PaymentRest(CustomCardStatusOrderDetail.this) {

                    @Override
                    public String getStatusOrder() {
                        return orderDetailModelList.get(0).getOrderHeaderStatusWaiting();
                    }

                    @Override
                    public String getPaymentData() {
                        OrderDetailDBManager.init(activity);
                        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                        ArrayList<OrderDetailItemDTO> orderDetailItemDTOList = new ArrayList<OrderDetailItemDTO>();
                        for (OrderDetailModel orderDetailModelData : orderDetailModelList) {
                            OrderDetailItemDTO orderDetailItemDTO = new OrderDetailItemDTO();
                            orderDetailItemDTO.setMenuName(orderDetailModelData.getMenuName());
                            orderDetailItemDTO.setMenuCode(orderDetailModelData.getMenuCode());
                            orderDetailItemDTO.setOrderDetailTotalAmount(orderDetailModelData.getOrderDetailTotalAmount());
                            orderDetailItemDTO.setOrderDetailTotalOrder(orderDetailModelData.getOrderDetailTotalOrder());
                            orderDetailItemDTO.setTrainCode(orderDetailModelData.getTrainCode());
                            orderDetailItemDTO.setCarriageCode(orderDetailModelData.getCarriageCode());
                            orderDetailItemDTO.setSeatCode(orderDetailModelData.getSeatCode());
                            orderDetailItemDTO.setOrderHeaderStatus(orderDetailModelData.getOrderHeaderStatusWaiting());
                            orderDetailItemDTOList.add(orderDetailItemDTO);
                            OrderDetailDBManager.getInstance().deleteEntity(orderDetailModelData.getId());
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
                        return activity;
                    }
                };

                PropertyUtil securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), activity);
                if(orderDetailModel.getOrderHeaderStatus().equals(String.valueOf(IOrderStatusConstant.RETRY)))
                    paymentRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
                else
                    updateOrderStatusRest.execute(securityUtil.getValuePropertyMap(PropertyConstant.CHIPPER_AUTH.toString()));
            }
        });

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        TextView txtOrderStatus = (TextView) view.findViewById(R.id.txtOrderStatus);
        ImageView imgOrderType = (ImageView) view.findViewById(R.id.imgThumbOrder);
        if(imgOrderType != null){
            Picasso.with(getContext()).load(iCustomCardStatus.getImageOrderType(Integer.parseInt(orderDetailModel.getOrderHeaderStatus()))).into(imgOrderType);
        }
        if(txtOrderStatus != null){
            txtOrderStatus.setText(iCustomCardStatus.getStatusCode(Integer.parseInt(orderDetailModel.getOrderHeaderStatus())));
        }
    }


    @Override
    public void onPostUpdateOrderStatus(Object objectResult) {
        if( objectResult != null ){
            OrderHeaderDTO orderHeaderDTO= (OrderHeaderDTO) objectResult;
            if(orderHeaderDTO.getErr_code().equals(ViewConstant.ZERO.toString())){
                FragmentOrderList fragmentOrderList = new FragmentOrderList();
                FragmentManager mFragmentManager = activity.getSupportFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderList).commit();
            }else{
                Toast.makeText(getContext(), "An error occurred, please check your connection", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "An error occurred, please check your connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPostSyncPayment(Object objectResult) {
        if( objectResult != null ){
            OrderDetailDTO orderDetailDTO= (OrderDetailDTO) objectResult;
            if(orderDetailDTO.getErr_code().equals(ViewConstant.ZERO.toString())){
                /*Delete Header Data*/
                OrderListDBManager.init(activity);
                OrderListModel orderListModel = OrderListDBManager.getInstance().getDataFromQuery(ModelConstant.ORDER_LIST_ORDER_ID, orderDetailModel.getOrderHeaderNo());

                OrderListDBManager.getInstance().deleteEntity(orderListModel.getId());


                printOrder(orderDetailDTO);
                FragmentOrderList fragmentOrderList = new FragmentOrderList();
                FragmentManager mFragmentManager = activity.getSupportFragmentManager();
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderList).commit();
            }else{
                Toast.makeText(getContext(), "An error occurred, please check your connection1", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "An error occurred, please check your connection2", Toast.LENGTH_SHORT).show();
        }
    }

    public void printOrder(OrderDetailDTO orderDetailDTO){
        BluetoothEngine bluetoothEngine = new BluetoothEngine(activity);
        try{
            String orderNo = orderDetailDTO.getOrderDetailItemDTOs().get(0).getOrderHeaderNo();
            PrintMessageDTO printMessageDTO = new PrintMessageDTO();
            List<OrderTempModel> orderTempModelList = new ArrayList<OrderTempModel>();
            BigDecimal totalOrder = new BigDecimal(0);
            for(OrderDetailItemDTO orderDetailItemDTO : orderDetailDTO.getOrderDetailItemDTOs()){
                OrderTempModel orderTempModel = new OrderTempModel();
                orderTempModel.setOrderNo(orderDetailItemDTO.getOrderHeaderNo());
                orderTempModel.setPrice(orderDetailItemDTO.getOrderDetailTotalAmount());
                orderTempModel.setQuantity(orderDetailItemDTO.getOrderDetailTotalOrder());
                orderTempModel.setMenuCode(orderDetailItemDTO.getMenuCode());
                orderTempModel.setMenuName(orderDetailItemDTO.getMenuName());
                orderTempModel.setSeatCode(orderDetailItemDTO.getSeatCode());
                orderTempModel.setCarriageCode(orderDetailItemDTO.getCarriageCode());
                orderTempModel.setTrainCode(orderDetailItemDTO.getTrainCode());
                orderTempModelList.add(orderTempModel);
                totalOrder = totalOrder.add(new BigDecimal(orderDetailModel.getOrderDetailTotalAmount()));
            }
            printMessageDTO.setOrderNo(orderNo);
            printMessageDTO.setTotal(String.valueOf(totalOrder));
            printMessageDTO.setMessageItemDTOs(orderTempModelList);
            Log.d("PRINT", printMessageDTO.toString());
            if(bluetoothEngine.checkOnBluetooth()){
                if(bluetoothEngine.openBluetoothConnection()) {
                    bluetoothEngine.printTemplateString(bluetoothEngine.templateMessageDto(printMessageDTO));
                }else{
                    Toast.makeText(activity,"Error Bluetooth Connection!",Toast.LENGTH_SHORT).show();
                }
            }else{
                /*Toast.makeText(rootView.getContext(),"Please Active Bluetooth!",Toast.LENGTH_SHORT).show();*/
                bluetoothEngine.activeBluetooth();
            }
        }catch (Exception e){
            Toast.makeText(activity, e.getMessage().concat("A error occurred Bluetooth isn't available!"), Toast.LENGTH_SHORT).show();
        }
    }
}
