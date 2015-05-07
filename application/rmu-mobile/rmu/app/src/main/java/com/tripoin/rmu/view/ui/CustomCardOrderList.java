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

import com.squareup.picasso.Picasso;
import com.tripoin.rmu.R;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderDetail;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class CustomCardOrderList extends Card {

    private OrderListModel orderListModel;
    private TextView txtOrderId;
    private ImageView imgOrderType;
    private TextView txtCarriage;
    private TextView txtSeat;
    private TextView txtTotalPaid;
    private TextView txtOrderTime;
    private FragmentActivity activity;

    public CustomCardOrderList(Context context) {
        super(context);
    }

    public CustomCardOrderList(FragmentActivity context, int innerLayout, OrderListModel orderListModel) {
        super(context, innerLayout);
        this.activity = context;
        this.orderListModel = orderListModel;
        init();
    }

    private void init(){
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                FragmentManager mFragmentManager = activity.getSupportFragmentManager();
                FragmentOrderDetail fragmentOrderDetail = new FragmentOrderDetail().newInstance(txtOrderId.getText().toString());
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderDetail).commit();
            }
        });

        setOnLongClickListener(new OnLongCardClickListener() {
            @Override
            public boolean onLongClick(Card card, View view) {
                ((Vibrator) getContext().getSystemService(getContext().VIBRATOR_SERVICE)).vibrate(200);
                Toast.makeText(getContext(), "Selected Order Id = ".concat(txtOrderId.getText().toString()), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        txtOrderId = (TextView) view.findViewById(R.id.txtOrderId);
        txtCarriage = (TextView) view.findViewById(R.id.txtCarriage);
        txtSeat = (TextView) view.findViewById(R.id.txtSeat);
        txtTotalPaid = (TextView) view.findViewById(R.id.txtTotalPaid);
        txtOrderTime = (TextView) view.findViewById(R.id.txtOrderTime);
        imgOrderType = (ImageView) view.findViewById(R.id.imgThumbOrder);

        if(txtOrderId != null){
            txtOrderId.setText(orderListModel.getOrderId());
        }
        if(txtCarriage != null){
            txtCarriage.setText(ViewConstant.CARRIAGE_NO.toString().concat(ViewConstant.SPACE.toString()).concat(orderListModel.getCarriageNumber()));
        }
        if(txtSeat != null){
            txtSeat.setText(ViewConstant.SEAT_NO.toString().concat(ViewConstant.SPACE.toString()).concat(orderListModel.getSeatNumber()));
        }
        if(txtTotalPaid != null){
            txtTotalPaid.setText(ViewConstant.IDR.toString().concat(ViewConstant.SPACE.toString()).concat(orderListModel.getTotalPaid()));
        }
        if(txtOrderTime != null){
            txtOrderTime.setText(orderListModel.getOrderTime());
        }
        if(imgOrderType != null){
            Picasso.with(getContext()).load(getImageOrderType(orderListModel.getProcessStatus())).into(imgOrderType);
        }
    }

    private int getImageOrderType(int processStatus){
        int result = 0;
        if(processStatus == 1){
            result = R.drawable.ic_list_new;
        }else if(processStatus == 2){
            result = R.drawable.ic_list_process;
        }else if(processStatus == 3){
            result = R.drawable.ic_list_delivery;
        }else if(processStatus == 4){
            result = R.drawable.ic_list_cancel;
        }else if(processStatus == 5) {
            result = R.drawable.ic_list_cancel;
        }else if(processStatus == 6) {
            result = R.drawable.ic_list_cancel;
        }
        return result;
    }
}
