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
import com.tripoin.rmu.model.persist.OrderListModel;
import com.tripoin.rmu.model.persist.OrderTempModel;
import com.tripoin.rmu.view.enumeration.ViewConstant;
import com.tripoin.rmu.view.fragment.impl.FragmentAddMenu;
import com.tripoin.rmu.view.fragment.impl.FragmentMenuList;
import com.tripoin.rmu.view.fragment.impl.FragmentOrderDetail;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Achmad Fauzi on 4/25/2015 : 12:26 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class CustomCardOrderTemp extends Card {

    private OrderTempModel orderTempModel;
    private TextView txtMenuName;
    private ImageView imgOrderTemp;
    private TextView txtQuantity;
    private TextView txtPrice;
    private FragmentActivity activity;

    public CustomCardOrderTemp(Context context) {
        super(context);
    }

    public CustomCardOrderTemp(FragmentActivity context, int innerLayout, OrderTempModel orderTempModel) {
        super(context, innerLayout);
        this.activity = context;
        this.orderTempModel = orderTempModel;
        Log.d("ORDERTEMPMODEL", orderTempModel.toString());
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
        txtMenuName = (TextView) view.findViewById(R.id.txtMenuName);
        txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
        txtPrice = (TextView) view.findViewById(R.id.txtPrice);
        imgOrderTemp = (ImageView) view.findViewById(R.id.imgThumbOrderTemp);

        if(txtMenuName != null){
            txtMenuName.setText(orderTempModel.getMenuName());
        }
        if(txtQuantity != null){
            txtQuantity.setText(orderTempModel.getQuantity());
        }
        if(txtPrice != null){
            txtPrice.setText(orderTempModel.getPrice());
        }
        if(imgOrderTemp != null){
            imgOrderTemp.setImageResource(R.drawable.ic_delete_black_24dp);
        }
        else {
            Log.d("SOMETHING", "is empty");
        }
    }
}
