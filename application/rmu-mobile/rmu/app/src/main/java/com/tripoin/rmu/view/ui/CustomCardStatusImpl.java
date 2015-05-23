package com.tripoin.rmu.view.ui;

import android.content.Context;

import com.tripoin.rmu.R;
import com.tripoin.rmu.feature.scheduler.constant.IOrderStatusConstant;
import com.tripoin.rmu.view.fragment.api.ICustomCardStatus;

/**
 * Created by Achmad Fauzi on 5/11/2015 : 8:34 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class CustomCardStatusImpl implements ICustomCardStatus {

    private Context context;

    public CustomCardStatusImpl(Context context) {
        this.context = context;
    }

    @Override
    public int getImageOrderType(int processStatus) {
        int result = 0;
        if(processStatus == IOrderStatusConstant.NEW){
            result = R.drawable.ic_list_new;
        }else if(processStatus == IOrderStatusConstant.PROCESS){
            result = R.drawable.ic_list_process;
        }else if(processStatus == IOrderStatusConstant.PREPARED){
            result = R.drawable.ic_list_prepared;
        }else if(processStatus == IOrderStatusConstant.DONE){
            result = R.drawable.ic_list_delivery;
        }else if(processStatus == IOrderStatusConstant.CANCEL) {
            result = R.drawable.ic_list_cancel;
        }else if(processStatus == IOrderStatusConstant.PENDING) {
            result = R.drawable.ic_list_pending;
        }else if(processStatus == IOrderStatusConstant.RETRY) {
            result = R.drawable.ic_list_retry;
        }
        return result;
    }

    public String getStatusCode(int statusNumber){
        String result = "";
        switch (statusNumber){
            case 1 : result = context.getString(R.string.status_new); break;
            case 2 : result = context.getString(R.string.status_processed); break;
            case 3 : result = context.getString(R.string.status_prepared); break;
            case 4 : result = context.getString(R.string.status_done); break;
            case 5 : result = context.getString(R.string.status_canceled); break;
            case 6 : result = context.getString(R.string.status_pending); break;
            case 7 : result = context.getString(R.string.status_retry); break;
        }
        return result;
    }
}
