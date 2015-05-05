package com.tripoin.rmu.rest.base;

import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripoin.rmu.common.IApplicationSetup;
import com.tripoin.rmu.rest.api.IBaseRest;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.rest.util.JSONParser;
import com.tripoin.rmu.rest.util.SSLJSONParser;
import com.tripoin.rmu.rest.api.IJSONParser;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.util.impl.PropertyUtil;
import com.tripoin.rmu.view.enumeration.ViewConstant;

import org.json.JSONObject;


/**
 * Created by Achmad Fauzi on 11/25/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 *
 * Base class Rest Communication
 */
public abstract class ABaseRest extends AsyncTask< String, String, String > implements IBaseRest, IApplicationSetup {

    protected ObjectMapper objectMapper = new ObjectMapper();
    protected Object objectResult;
    protected JSONObject jsonObject;
    protected PropertyUtil propertyUtil = new PropertyUtil(PropertyConstant.PROPERTY_FILE_NAME.toString(), getContext());


    protected ABaseRest() {
    }

    @Override
    public abstract Context getContext();

    @Override
    public abstract String initUrl();

    @Override
    public abstract Class<?> initClassResult();

    @Override
    public Object getObjectResult() {
        return objectResult;
    }

    @Override
    public String constructBaseURL(){
        return
            RestConstant.HTTP_REST.toString().
            concat(ViewConstant.COLON.toString().
            concat(ViewConstant.SLASH.toString()).
            concat(ViewConstant.SLASH.toString()).
            concat(getServerHost()).
            concat(ViewConstant.COLON.toString()).
            concat(getServerPort()).
            concat(ViewConstant.SLASH.toString()).
            concat(RestConstant.WS_CONTEXT.toString()));
    }

    @Override
    public String getApplicationMode(){
        return PropertyConstant.APP_MODE.toString();
    }

    @Override
    public String getServerHost(){
        return propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_HOST_KEY.toString());
    }

    @Override
    public String getServerPort(){
        return propertyUtil.getValuePropertyMap(PropertyConstant.SERVER_PORT_KEY.toString());
    }

    public IJSONParser getJsonParser(){
        if( getApplicationMode().equals(PropertyConstant.DEVELOPMENT.toString()) ){
            return new JSONParser();
        }else if( getApplicationMode().equals(PropertyConstant.PRODUCTION.toString()) ){
            return new SSLJSONParser();
        }else{
            return null;
        }
    }

    @Override
    public String processedURL() {
        return constructBaseURL().concat(initUrl());
    }
}
