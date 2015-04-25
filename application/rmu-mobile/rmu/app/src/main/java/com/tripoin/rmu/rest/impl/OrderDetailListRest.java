package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.order_detail.OrderDetailDTO;
import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/19/2015.
 */
public class OrderDetailListRest extends ARestGETAsyncTask {


    protected OrderDetailListRest(IBaseRestFinished iBaseRestFinished) {
        super(iBaseRestFinished);
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_ORDER_DETAIL.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return OrderDetailDTO.class;
    }
}
