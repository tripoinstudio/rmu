package com.tripoin.rmu.view.ui;

import android.content.Context;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tripoin.rmu.R;
import com.tripoin.rmu.model.persist.OrderDetailModel;
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.api.ICustomCardStatus;
import com.tripoin.rmu.view.fragment.base.ABaseCustomCard;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderDetail;

import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class CustomCardOrderList extends ABaseCustomCard {

    private OrderListModel orderListModel;
    private TextView txtOrderId;
    private FragmentActivity activity;
    private ICustomCardStatus iCustomCardStatus;

    public CustomCardOrderList(Context context) {
        super(context);
    }

    public CustomCardOrderList(FragmentActivity context, int innerLayout, OrderListModel orderListModel) {
        super(context, innerLayout);
        this.activity = context;
        this.orderListModel = orderListModel;
        iCustomCardStatus = new CustomCardStatusImpl(context);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        txtOrderId = (TextView) view.findViewById(R.id.txtOrderId);
        TextView txtCarriage = (TextView) view.findViewById(R.id.txtCarriage);
        TextView txtSeat = (TextView) view.findViewById(R.id.txtSeat);
        TextView txtTotalPaid = (TextView) view.findViewById(R.id.txtTotalPaid);
        TextView txtOrderTime = (TextView) view.findViewById(R.id.txtOrderTime);
        ImageView imgOrderType = (ImageView) view.findViewById(R.id.imgThumbOrder);

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
            Picasso.with(getContext()).load(iCustomCardStatus.getImageOrderType(orderListModel.getProcessStatus())).into(imgOrderType);
        }
    }

    @Override
    public void initActions() {
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                FragmentManager mFragmentManager = activity.getSupportFragmentManager();
                FragmentOrderDetail fragmentOrderDetail = new FragmentOrderDetail().newInstance(txtOrderId.getText().toString());
                mFragmentManager.beginTransaction().replace(R.id.container, fragmentOrderDetail).addToBackStack(null).commit();
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
}
