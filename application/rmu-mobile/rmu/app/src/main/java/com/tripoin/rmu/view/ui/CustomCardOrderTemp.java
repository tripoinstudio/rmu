package com.tripoin.rmu.view.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.persist.OrderTempModel;
import com.tripoin.rmu.persistence.orm_persistence.service.OrderTempDBManager;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.impl.FragmentAddMenu;

import java.math.BigDecimal;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class CustomCardOrderTemp extends Card {

    private OrderTempModel orderTempModel;
    private FragmentActivity activity;

    public CustomCardOrderTemp(FragmentActivity context, int innerLayout, OrderTempModel orderTempModel) {
        super(context, innerLayout);
        this.activity = context;
        this.orderTempModel = orderTempModel;
        OrderTempDBManager.init(context);
        init();
    }

    private void init(){
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                FragmentManager mFragmentManager = activity.getSupportFragmentManager();
                FragmentAddMenu fragmentAddMenu = new FragmentAddMenu().newInstance(orderTempModel);
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentAddMenu).addToBackStack(null).commit();
                Log.d("CLICK", "I am clicked");
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        TextView txtMenuName = (TextView) view.findViewById(R.id.txtMenuName);
        TextView txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
        TextView txtPrice = (TextView) view.findViewById(R.id.txtPrice);

        if(txtMenuName != null){
            txtMenuName.setText(orderTempModel.getMenuName());
        }
        if(txtQuantity != null){
            txtQuantity.setText(ViewConstant.AT.toString().concat(ViewConstant.SPACE.toString()).concat(orderTempModel.getQuantity()).concat(" pcs"));
        }
        if(txtPrice != null){
            txtPrice.setText(orderTempModel.getPrice().concat(ViewConstant.CURRENCY_PATTERN.toString()));
        }
    }

    @Override
    public OnSwipeListener getOnSwipeListener() {
        OrderTempDBManager.getInstance().deleteEntity(orderTempModel.getId());
        BigDecimal totalOrder = new BigDecimal(0);
        List<OrderTempModel> orderTempModelList = OrderTempDBManager.getInstance().getAllData();
        if(orderTempModelList != null){
            for(OrderTempModel orderTempModel : orderTempModelList){
                totalOrder = new BigDecimal(orderTempModel.getPrice()).add(totalOrder);
            }
        }
        TextView menuTotal = (TextView)activity.findViewById(R.id.lbl_menu_total);
        menuTotal.setText(ViewConstant.CURRENCY_IDR.toString().concat(String.valueOf(totalOrder)).concat(ViewConstant.CURRENCY_PATTERN.toString()));
        return super.getOnSwipeListener();
    }
}
