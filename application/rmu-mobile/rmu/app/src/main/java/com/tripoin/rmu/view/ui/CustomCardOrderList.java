package com.tripoin.rmu.view.ui;

import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tripoin.rmu.R;
import com.tripoin.rmu.model.DTO.order_list.OrderListDTO;
import com.tripoin.rmu.view.enumeration.ViewConstant;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class CustomCardOrderList extends Card {

    private OrderListDTO orderListDTO;
    private TextView txtOrderId;
    private ImageView imgOrderType;
    private TextView txtCarriage;
    private TextView txtSeat;
    private TextView txtTotalPaid;
    private TextView txtOrderTime;

    public CustomCardOrderList(Context context) {
        super(context);
    }

    public CustomCardOrderList(Context context, int innerLayout, OrderListDTO orderListDTO) {
        super(context, innerLayout);
        this.orderListDTO = orderListDTO;
        init();
    }

    private void init(){
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
            txtOrderId.setText(orderListDTO.getOrderId());
        }
        if(txtCarriage != null){
            txtCarriage.setText(ViewConstant.CARRIAGE_NO.toString().concat(ViewConstant.SPACE.toString()).concat(orderListDTO.getCarriageNumber()));
        }
        if(txtSeat != null){
            txtSeat.setText(ViewConstant.SEAT_NO.toString().concat(ViewConstant.SPACE.toString()).concat(orderListDTO.getSeatNumber()));
        }
        if(txtTotalPaid != null){
            txtTotalPaid.setText(ViewConstant.IDR.toString().concat(ViewConstant.SPACE.toString()).concat(orderListDTO.getTotalPaid()));
        }
        if(txtOrderTime != null){
            txtOrderTime.setText(orderListDTO.getOrderTime());
        }
        if(imgOrderType != null){
            //imgOrderType.setImageDrawable(new RoundedImage(BitmapFactory.decodeResource(view.getResources(), getImageOrderType(orderListDTO.getProcessStatus()))));
            imgOrderType.setImageResource(getImageOrderType(orderListDTO.getProcessStatus()));
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
}
