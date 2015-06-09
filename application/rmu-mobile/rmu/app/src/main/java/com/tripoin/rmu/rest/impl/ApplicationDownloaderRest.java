package com.tripoin.rmu.rest.impl;

import android.content.Context;
import android.os.AsyncTask;

import com.tripoin.rmu.feature.version.AppUpdate;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;

/**
 * Created by Achmad Fauzi on 6/7/2015 : 4:34 PM.
 * mail : achmad.fauzi@sigma.co.id
 */
public abstract class ApplicationDownloaderRest extends AsyncTask {

    private PropertyUtil propertyUtil;

    protected ApplicationDownloaderRest(PropertyUtil propertyUtil) {
        this.propertyUtil = propertyUtil;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        AppUpdate appUpdate = new AppUpdate(
                baseURL
                .concat(RestConstant.TSK_APK.toString()
                .concat(ViewConstant.SLASH.toString())
                .concat(params[0].toString())),
                PropertyConstant.PROPERTIES_PATH.toString().concat(params[0].toString())
        );
        appUpdate.setContext(getContext());
        appUpdate.downloadImage();
        return null;
    }

    public abstract Context getContext();

    public final String baseURL =
                    RestConstant.HTTP_REST.toString()
                    .concat(ViewConstant.COLON.toString()
                    .concat(ViewConstant.SLASH.toString())
                    .concat(ViewConstant.SLASH.toString())
                    .concat(getServerHost())
                    .concat(ViewConstant.COLON.toString())
                    .concat(getServerPort())
                    .concat(ViewConstant.SLASH.toString())
                    .concat(RestConstant.WS_CONTEXT.toString()));

    public String getServerHost(){
        return propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_HOST_KEY.toString());
    }

    public String getServerPort(){
        return propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_PORT_KEY.toString());
    }
}
