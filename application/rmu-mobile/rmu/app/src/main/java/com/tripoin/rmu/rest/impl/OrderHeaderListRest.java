package com.tripoin.rmu.rest.impl;

import android.content.Context;

import com.tripoin.rmu.model.DTO.order_header.OrderHeaderDTO;
import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.api.IOrderListPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/18/2015.
 */
public abstract class OrderHeaderListRest extends ARestDialogGETAsyncTask{

    private IOrderListPost iOrderListPost;

    protected OrderHeaderListRest(IOrderListPost iOrderListPost) {
        this.iOrderListPost = iOrderListPost;
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_ORDER_HEADER.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return OrderHeaderDTO.class;
    }

    @Override
    protected String getProgressDialogTitle() {
        return "Synchronizing Order List";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iOrderListPost.onPostSynchronize(objectResult);
    }
}
