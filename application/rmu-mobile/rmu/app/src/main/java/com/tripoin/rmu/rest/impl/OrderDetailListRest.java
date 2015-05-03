package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.order_detail.OrderDetailDTO;
import com.tripoin.rmu.rest.api.IOrderDetailPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.view.enumeration.ViewConstant;

/**
 * Created by Ridla on 4/19/2015.
 */
public abstract class OrderDetailListRest extends ARestDialogGETAsyncTask{

    private IOrderDetailPost iOrderDetailPost;

    protected OrderDetailListRest(IOrderDetailPost iOrderDetailPost) {
        this.iOrderDetailPost = iOrderDetailPost;
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_ORDER_DETAIL.toString().concat(ViewConstant.QUESTION.toString()).concat(RestConstant.ORDER_HEADER_NO.toString()).concat(ViewConstant.EQUALS.toString()).concat(getOrderHeaderId());
    }

    @Override
    public Class<?> initClassResult() {
        return OrderDetailDTO.class;
    }

    @Override
    protected String getProgressDialogTitle() {
        return "Synchronizing order detail";
    }

    public abstract String getOrderHeaderId();

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iOrderDetailPost.onPostSynchronize(objectResult);
    }
}
