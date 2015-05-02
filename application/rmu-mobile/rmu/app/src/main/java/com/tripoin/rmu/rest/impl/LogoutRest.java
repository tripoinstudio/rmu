package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.base.impl.BaseRESTDTO;
import com.tripoin.rmu.rest.api.ILogoutPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Achmad Fauzi on 4/28/2015 : 10:59 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public abstract  class LogoutRest extends ARestDialogGETAsyncTask {

    private ILogoutPost iLogoutPost;

    protected LogoutRest(ILogoutPost iLogoutPost) {
        this.iLogoutPost = iLogoutPost;
    }

    @Override
    protected String getProgressDialogTitle() {
        return "Sign out";
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_LOGOUT.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return BaseRESTDTO.class;
    }
}
