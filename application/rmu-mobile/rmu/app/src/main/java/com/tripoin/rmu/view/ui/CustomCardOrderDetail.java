package com.tripoin.rmu.view.ui;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.view.enumeration.ViewConstant;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class CustomCardOrderDetail extends Card {

    private OrderDetailModel orderDetailModel;
    private TextView txtMenuName;
    private TextView txtTotalOrder;
    private TextView txtTotalPaid;

    private FragmentActivity activity;

    public CustomCardOrderDetail(Context context) {
        super(context);
    }

    public CustomCardOrderDetail(FragmentActivity context, int innerLayout, OrderDetailModel orderDetailModel) {
        super(context, innerLayout);
        this.activity = context;
        this.orderDetailModel = orderDetailModel;
        init();
    }

    private void init(){
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        txtMenuName = (TextView) view.findViewById(R.id.txtMenuName);
        txtTotalOrder = (TextView) view.findViewById(R.id.txtTotalOrder);
        txtTotalPaid = (TextView) view.findViewById(R.id.txtTotalPaid);

        if(txtMenuName != null){
            txtMenuName.setText(orderDetailModel.getMenuName());
        }
        if(txtTotalOrder != null){
            txtTotalOrder.setText(ViewConstant.TOTAL_ORDER.toString().concat(ViewConstant.SPACE.toString()).concat(orderDetailModel.getOrderDetailTotalOrder()));
        }
        if(txtTotalPaid != null){
            txtTotalPaid.setText(ViewConstant.TOTAL_PAID.toString().concat(ViewConstant.SPACE.toString()).concat(orderDetailModel.getOrderDetailTotalAmount()));
        }
    }

}
