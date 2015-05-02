package com.tripoin.rmu.rest.base;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripoin.rmu.common.IApplicationSetup;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.rest.util.JSONParser;
import com.tripoin.rmu.rest.util.SSLJSONParser;
import com.tripoin.rmu.rest.api.IJSONParser;
import com.tripoin.rmu.util.enumeration.PropertyConstant;
import com.tripoin.rmu.view.enumeration.ViewConstant;

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


    protected ABaseRest() {
    }

    public static String BASE_URL =
            RestConstant.HTTP_REST.toString().
            concat(ViewConstant.COLON.toString().
            concat(ViewConstant.SLASH.toString()).
            concat(ViewConstant.SLASH.toString()).
            concat(PropertyConstant.SERVER_HOST_DEFAULT_VALUE.toString()).
            concat(ViewConstant.COLON.toString()).
            concat(PropertyConstant.SERVER_PORT_DEFAULT_VALUE.toString()).
            concat(ViewConstant.SLASH.toString()).
            concat(RestConstant.WS_CONTEXT.toString()));

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
