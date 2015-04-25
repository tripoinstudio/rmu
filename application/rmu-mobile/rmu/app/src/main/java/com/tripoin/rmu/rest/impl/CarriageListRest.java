package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.carriage.CarriageDTO;
import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.base.ABaseDialogRest;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Dayat on 4/18/2015.
 */
public class CarriageListRest extends ARestGETAsyncTask{


    protected CarriageListRest(IBaseRestFinished iBaseRestFinished) {
        super(iBaseRestFinished);
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_CARRIAGE.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return CarriageDTO.class;
    }
}
