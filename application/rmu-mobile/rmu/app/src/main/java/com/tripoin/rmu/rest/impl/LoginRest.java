package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.user.UserDTO;
import com.tripoin.rmu.rest.api.ILoginPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Achmad Fauzi on 4/16/2015 : 8:56 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public abstract class LoginRest extends ARestDialogGETAsyncTask {

    private ILoginPost iLoginPost;

    public LoginRest(ILoginPost iLoginPost) {
        super(iLoginPost);
        this.iLoginPost = iLoginPost;
    }

    @Override
    protected String getProgressDialogTitle() {
        return RestConstant.AUTHENTICATING.toString();
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_LOGIN.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return UserDTO.class;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iLoginPost.onPostDelegate(objectResult);
    }
}


