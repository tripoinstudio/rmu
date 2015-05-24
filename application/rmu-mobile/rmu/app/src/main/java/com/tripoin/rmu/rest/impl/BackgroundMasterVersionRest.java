package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.base.impl.BaseRESTDTO;
import com.tripoin.rmu.rest.api.IMasterVersionPost;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;

/**
 * Created by Achmad Fauzi on 5/23/2015 : 9:56 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public abstract class BackgroundMasterVersionRest extends ARestGETAsyncTask {

    private IMasterVersionPost iMasterVersionPost;

    protected BackgroundMasterVersionRest(IMasterVersionPost iMasterVersionPost) {
        this.iMasterVersionPost= iMasterVersionPost;
    }

    @Override
    public String initUrl() {
        return "version";
    }

    @Override
    public Class<?> initClassResult() {
        return BaseRESTDTO.class;
    }

    @Override
    protected void onPostExecute(String s) {
        iMasterVersionPost.onSyncMasterVersionPost(objectResult);
    }
}
