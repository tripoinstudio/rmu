package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.carriage.CarriageDTO;
import com.tripoin.rmu.model.DTO.menu.MenuDTO;
import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.api.ICarriagePost;
import com.tripoin.rmu.rest.api.IMenuPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Dayat on 4/18/2015.
 */
public abstract class CarriageListRest extends ARestDialogGETAsyncTask{

    private ICarriagePost iCarriagePost;

    protected CarriageListRest(ICarriagePost iCarriagePost) {
        this.iCarriagePost = iCarriagePost;
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_CARRIAGE.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return CarriageDTO.class;
    }


    @Override
    protected String getProgressDialogTitle() {
        return "Retrieving carriage";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iCarriagePost.onPostSyncCarriage(objectResult);
    }
}
