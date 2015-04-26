package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.seat.SeatDTO;
import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/18/2015.
 */
public class SeatListRest extends ARestGETAsyncTask{


    protected SeatListRest(IBaseRestFinished iBaseRestFinished) {
        super(iBaseRestFinished);
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_SEAT.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return SeatDTO.class;
    }
}