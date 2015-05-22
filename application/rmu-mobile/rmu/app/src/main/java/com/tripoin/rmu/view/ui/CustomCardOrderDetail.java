package com.tripoin.rmu.view.ui;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.base.ABaseCustomCard;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class CustomCardOrderDetail extends ABaseCustomCard {

    private OrderDetailModel orderDetailModel;

    public CustomCardOrderDetail(FragmentActivity context, int innerLayout, OrderDetailModel orderDetailModel) {
        super(context, innerLayout);
        this.orderDetailModel = orderDetailModel;
    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        TextView txtMenuName = (TextView) view.findViewById(R.id.txtMenuName);
        TextView txtTotalOrder = (TextView) view.findViewById(R.id.txtTotalOrder);
        TextView txtTotalPaid = (TextView) view.findViewById(R.id.txtTotalPaid);

        if(txtMenuName != null){
            txtMenuName.setText(orderDetailModel.getMenuName());
        }
        if(txtTotalOrder != null){
            txtTotalOrder.setText(ViewConstant.TOTAL_ORDER.toString().concat(ViewConstant.SPACE.toString()).concat(orderDetailModel.getOrderDetailTotalOrder()));
        }
        if(txtTotalPaid != null){
            txtTotalPaid.setText(orderDetailModel.getOrderDetailTotalAmount());
        }
    }

    @Override
    public void initActions() {
        /*There is no required options here*/
    }
}
