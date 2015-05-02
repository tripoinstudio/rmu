package com.tripoin.rmu.rest.impl;

import android.content.Context;

import com.tripoin.rmu.model.DTO.menu.MenuDTO;
import com.tripoin.rmu.rest.api.IBaseRestFinished;
import com.tripoin.rmu.rest.api.IMenuPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

/**
 * Created by Ridla on 4/18/2015.
 */
public abstract class MenuListRest extends ARestDialogGETAsyncTask{

    private IMenuPost iMenuPost;

    protected MenuListRest(IMenuPost iMenuPost) {
        this.iMenuPost = iMenuPost;
    }

    @Override
    public String initUrl() {
        return RestConstant.TSK_MENU.toString();
    }

    @Override
    public Class<?> initClassResult() {
        return MenuDTO.class;
    }


    @Override
    protected String getProgressDialogTitle() {
        return "Retrieving menu";
    }
    
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iMenuPost.onPostSyncMenu(objectResult);
    }
}
