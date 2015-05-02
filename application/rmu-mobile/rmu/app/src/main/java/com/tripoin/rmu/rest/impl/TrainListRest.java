package com.tripoin.rmu.rest.impl;

import android.content.Context;

import com.tripoin.rmu.model.DTO.train.TrainDTO;
import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.api.ICarriagePost;
import com.tripoin.rmu.rest.api.ITrainPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/18/2015.
 */
public abstract class TrainListRest extends ARestDialogGETAsyncTask {

    ITrainPost iTrainPost;

    protected TrainListRest(ITrainPost iTrainPost) {
        this.iTrainPost = iTrainPost;
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_TRAIN.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return TrainDTO.class;
    }

    @Override
    protected String getProgressDialogTitle() {
        return "Synchronizing Train";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iTrainPost.onPostSyncTrain(objectResult);
    }
}
