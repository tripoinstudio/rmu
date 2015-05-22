package com.tripoin.rmu.view.ui;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tripoin.rmu.R;
import com.tripoin.rmu.model.DTO.order_header.OrderHeaderDTO;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.rest.api.IUpdateOrderStatusPost;
import com.tripoin.rmu.rest.impl.UpdateOrderStatusRest;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ICustomCardStatus;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderList;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class CustomCardStatusOrderDetail extends Card implements IUpdateOrderStatusPost {

    private OrderDetailModel orderDetailModel;
    private FragmentActivity activity;
    private ICustomCardStatus iCustomCardStatus;

    public CustomCardStatusOrderDetail(FragmentActivity context, int innerLayout, OrderDetailModel orderDetailModel) {
        super(context, innerLayout);
        this.activity = context;
        this.orderDetailModel = orderDetailModel;
        iCustomCardStatus = new CustomCardStatusImpl(context);
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
                PropertyUtil securityUtil = new PropertyUtil(PropertyConstant.LOGIN_FILE_NAME.toString(), activity);
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
}
