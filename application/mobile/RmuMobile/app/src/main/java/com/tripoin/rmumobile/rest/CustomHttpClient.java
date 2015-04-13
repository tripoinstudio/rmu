package com.tripoin.rmumobile.rest;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import id.co.telkomsigma.ariumm_force.rest.enumeration.RestConstant;

/**
 * Created by Achmad Fauzi on 1/3/2015.
 * achmad.fauzi@sigma.co.id
 */
public class CustomHttpClient {

    private int timeOut = Integer.valueOf( RestConstant.HTTP_TIMEOUT.toString() ) * 1000;
    private HttpClient httpClient;

    public HttpClient getHttpClient(){
        if( httpClient == null ){
            httpClient = new DefaultHttpClient();
            final HttpParams params = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout( params, timeOut );
            HttpConnectionParams.setSoTimeout( params, timeOut );
            HttpProtocolParams.setUseExpectContinue( httpClient.getParams(), false );
            ConnManagerParams.setTimeout( params, timeOut );
        }
        return httpClient;
    }
}
