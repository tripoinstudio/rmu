package com.tripoin.rmumobile.rest.base;

import android.os.AsyncTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripoin.rmumobile.common.IApplicationSetup;
import com.tripoin.rmumobile.rest.JSONParser;
import com.tripoin.rmumobile.rest.SSLJSONParser;
import com.tripoin.rmumobile.rest.api.IJSONParser;
import com.tripoin.rmumobile.util.enumeration.PropertyConstant;

import org.json.JSONObject;


/**
 * Created by Achmad Fauzi on 11/25/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 *
 * Base class Rest Communication
 */
public abstract class ABaseRest extends AsyncTask< String, String, String > implements IApplicationSetup {

    protected ObjectMapper objectMapper = new ObjectMapper();
    protected Object objectResult;
    protected JSONObject jsonObject;

    public abstract String initUrl();

    public abstract Class<?> initClassResult();

    public Object getObjectResult() {
        return objectResult;
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
    public String getApplicationMode(){
        return PropertyConstant.APP_MODE.toString();
    }
}
