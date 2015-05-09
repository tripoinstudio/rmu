package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.order_header.OrderHeaderDTO;
import com.tripoin.rmu.rest.api.IOrderListPost;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/18/2015.
 */
public abstract class BackgroundOrderHeaderListRest extends ARestGETAsyncTask {

    private IOrderListPost iOrderListPost;

    protected BackgroundOrderHeaderListRest(IOrderListPost iOrderListPost) {
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iOrderListPost.onPostSynchronize(objectResult);
    }
}
