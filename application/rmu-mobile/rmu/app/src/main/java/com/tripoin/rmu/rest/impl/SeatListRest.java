package com.tripoin.rmu.rest.impl;

import android.content.Context;

import com.tripoin.rmu.model.DTO.seat.SeatDTO;
import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.api.ICarriagePost;
import com.tripoin.rmu.rest.api.ISeatPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/18/2015.
 */
public abstract class SeatListRest extends ARestDialogGETAsyncTask{

    private ISeatPost iSeatPost;

    protected SeatListRest(ISeatPost iSeatPost) {
        this.iSeatPost = iSeatPost;
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_SEAT.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return SeatDTO.class;
    }

    @Override
    protected String getProgressDialogTitle() {
        return "Retrieving seat";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iSeatPost.onPostSyncSeat(objectResult);
    }
}
