package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.order_header.OrderHeaderDTO;
import com.tripoin.rmu.rest.api.IUpdateOrderStatusPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.view.enumeration.ViewConstant;

/**
 * Created by Achmad Fauzi on 5/6/2015 : 3:13 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public abstract class UpdateOrderStatusRest extends ARestDialogGETAsyncTask {

    private IUpdateOrderStatusPost iUpdateOrderStatusPost;

    public UpdateOrderStatusRest(IUpdateOrderStatusPost iUpdateOrderStatusPost) {
        this.iUpdateOrderStatusPost = iUpdateOrderStatusPost;
    }

    @Override
    protected String getProgressDialogTitle() {
        return "Updating Order Detail";
    }

    @Override
    public String initUrl() {
        return
                RestConstant.TSK_SET_ORDER_STATUS.toString().
                concat(ViewConstant.QUESTION.toString()).
                concat(RestConstant.ORDER_NO.toString()).
                concat(ViewConstant.EQUALS.toString()).
                concat(getOrderHeaderId()).
                concat(RestConstant.AND_AMPERS.toString()).
                concat(RestConstant.STATUS.toString()).
                concat(ViewConstant.EQUALS.toString()).
                concat(getStatusTarget());
    }

    @Override
    public Class<?> initClassResult() {
        return OrderHeaderDTO.class;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iUpdateOrderStatusPost.onPostUpdateOrderStatus(objectResult);
    }

    public abstract String getOrderHeaderId();

    public abstract String getStatusTarget();
}
