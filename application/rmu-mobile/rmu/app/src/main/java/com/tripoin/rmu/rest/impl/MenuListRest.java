package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.menu.MenuDTO;
import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/18/2015.
 */
public class MenuListRest extends ARestGETAsyncTask{


    protected MenuListRest(IBaseRestFinished iBaseRestFinished) {
        super(iBaseRestFinished);
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_MENU.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return MenuDTO.class;
    }
}
