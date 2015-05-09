package com.tripoin.rmu.rest.base;

import android.util.Log;

import com.tripoin.rmu.rest.api.IBaseRestFinished;

import java.io.IOException;

/**
 * Created by Achmad Fauzi on 11/19/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * This Class is used to communicate with REST method POST without progress bar
 */
public abstract class ARestPOSTAsyncTask extends ABaseRest {

    @Override
    protected String doInBackground(String... params) {
        Log.e("URL WS", initUrl());
        try {
            jsonObject = getJsonParser().retrieveJSONAsGet(initUrl(), params[0]);
            objectResult = objectMapper.readValue( String.valueOf(jsonObject), initClassResult() );
        } catch (Exception e) {
            Log.e("ERROR WEB SERVICE", e.toString());
            objectResult = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
