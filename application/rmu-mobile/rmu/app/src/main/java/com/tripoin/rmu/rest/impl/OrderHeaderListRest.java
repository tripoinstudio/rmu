package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/18/2015.
 */
public class OrderHeaderListRest extends ARestGETAsyncTask{


    @Override
    public String initUrl() {
        return RestConstant.TSK_ORDER_HEADER.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return OrderHeaderListRest.class;
    }
}
