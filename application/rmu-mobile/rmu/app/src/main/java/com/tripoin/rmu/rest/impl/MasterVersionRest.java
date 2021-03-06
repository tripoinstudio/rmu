package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.master.MasterVersion;
import com.tripoin.rmu.rest.api.IMasterVersionPost;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Achmad Fauzi on 4/30/2015 : 11:01 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public abstract class MasterVersionRest extends ARestGETAsyncTask {

    private IMasterVersionPost iMasterVersionPost;

    public MasterVersionRest(IMasterVersionPost iMasterVersionPost) {
        this.iMasterVersionPost = iMasterVersionPost;
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_VERSION.toString();
        /*return RestConstant.TSK_VERSION.toString()
                .concat(ViewConstant.QUESTION.toString())
                .concat(RestConstant.LAST_VERSION.toString())
                .concat(ViewConstant.EQUALS.toString())
                .concat(getCurrentVersion());*/
    }

    @Override
    public Class<?> initClassResult() {
        return MasterVersion.class;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iMasterVersionPost.onSyncMasterVersionPost(objectResult);
    }
}
