package com.tripoin.rmu.view.ui;

import android.content.Context;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderDetail;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class CustomCardStatusOrderDetail extends Card {

    private OrderDetailModel orderDetailModel;
    private ImageView imgOrderType;
    private TextView txtOrderStatus;
    private FragmentActivity activity;

    public CustomCardStatusOrderDetail(Context context) {
        super(context);
    }

    public CustomCardStatusOrderDetail(FragmentActivity context, int innerLayout, OrderDetailModel orderDetailModel) {
        super(context, innerLayout);
        this.activity = context;
        this.orderDetailModel = orderDetailModel;
        init();
    }

    private void init(){

        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                /*FragmentManager mFragmentManager = activity.getSupportFragmentManager();
                FragmentOrderDetail fragmentOrderDetail = new FragmentOrderDetail().newInstance(txtOrderId.getText().toString());
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderDetail).commit();*/
                Toast.makeText(activity, "ORDER STATUS CARD", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        txtOrderStatus = (TextView) view.findViewById(R.id.txtOrderStatus);
        imgOrderType = (ImageView) view.findViewById(R.id.imgThumbOrder);
        if(imgOrderType != null){
            imgOrderType.setImageResource(getImageOrderType(Integer.parseInt(orderDetailModel.getOrderHeaderStatus())));
        }
        if(txtOrderStatus != null){
            txtOrderStatus.setText(getStatusCode(Integer.parseInt(orderDetailModel.getOrderHeaderStatus())));
        }
    }

    private int getImageOrderType(int processStatus){
        if(processStatus == 1){
            return R.drawable.ic_list_new;
        }else if(processStatus == 2){
            return R.drawable.ic_list_process;
        }else if(processStatus == 3){
            return R.drawable.ic_list_delivery;
        }else{
            return R.drawable.ic_list_cancel;
        }
    }

    private String getStatusCode(int statusNumber){
        String result = "";
        switch (statusNumber){
            case 1 : result = getContext().getString(R.string.status_new); break;
            case 2 : result = getContext().getString(R.string.status_processed); break;
            case 3 : result = getContext().getString(R.string.status_delivered); break;
            case 4 : result = getContext().getString(R.string.status_cancelled); break;
        }
        return result;
    }
}
