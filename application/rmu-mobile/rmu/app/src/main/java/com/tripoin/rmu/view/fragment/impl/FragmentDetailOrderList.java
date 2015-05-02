package com.tripoin.rmu.view.fragment.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripoin.rmu.R;

/**
 * Created by Achmad Fauzi on 5/2/2015 : 5:38 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class FragmentDetailOrderList extends Fragment {

    private View rootView;
    private static String ORDER_LIST_ID = "ORDER_LIST_ID";

    public FragmentDetailOrderList newInstance(String orderListId){
        FragmentDetailOrderList mFragment = new FragmentDetailOrderList();
        Bundle bundle = new Bundle();
        bundle.putString(ORDER_LIST_ID, orderListId);
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail_order_list, container, false);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT ));
        TextView txtOrderListId = (TextView) rootView.findViewById(R.id.txtOrderId);
        String orderListId = getArguments().getString(ORDER_LIST_ID);
        txtOrderListId.setText(orderListId);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
