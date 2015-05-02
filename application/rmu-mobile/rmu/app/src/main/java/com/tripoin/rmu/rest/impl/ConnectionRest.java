package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.base.impl.BaseRESTDTO;
import com.tripoin.rmu.rest.api.IConnectionPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/27/2015.
 */
public abstract class ConnectionRest extends ARestDialogGETAsyncTask {

    private IConnectionPost iConnectionPost;

    protected ConnectionRest(IConnectionPost iConnectionPost) {
        this.iConnectionPost = iConnectionPost;
    }

    @Override
    protected String getProgressDialogTitle() {
        return RestConstant.TESTING.toString();
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_CONNECTION.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return BaseRESTDTO.class;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iConnectionPost.onPostDelegate(objectResult);
    }
}
