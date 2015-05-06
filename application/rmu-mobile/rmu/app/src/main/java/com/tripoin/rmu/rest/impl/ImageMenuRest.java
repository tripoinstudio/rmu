package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.image.ImageDTO;
import com.tripoin.rmu.model.DTO.menu.MenuDTO;
import com.tripoin.rmu.rest.api.IImageMenuPost;
import com.tripoin.rmu.rest.api.IMenuPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.view.enumeration.ViewConstant;

/**
 * Created by Ridla on 4/18/2015.
 */
public abstract class ImageMenuRest extends ARestDialogGETAsyncTask{

    private IImageMenuPost iImageMenuPost;

    protected ImageMenuRest(IImageMenuPost iImageMenuPost) {
        this.iImageMenuPost = iImageMenuPost;
    }

    public abstract String getMenuCode();

    @Override
    public String initUrl() {
        return RestConstant.TSK_IMAGEMENU.toString().concat(ViewConstant.QUESTION.toString()).concat(RestConstant.MENU_CODE.toString()).concat(ViewConstant.EQUALS.toString()).concat(getMenuCode());
    }

    @Override
    public Class<?> initClassResult() {
        return ImageDTO.class;
    }


    @Override
    protected String getProgressDialogTitle() {
        return "Synchronize Image Menu";
    }
    
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iImageMenuPost.onPostSyncImageMenu(objectResult);
    }
}
