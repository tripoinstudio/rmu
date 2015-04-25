package com.tripoin.rmu.rest.api;

import org.json.JSONObject;

/**
 * Created by Achmad Fauzi on 2/25/2015.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * this will use Factory pattern to make instance of JSONParser
 */
public interface IJSONParser {

    /**
     * POST
     * @param url String
     * @return JSONObject
     */
    public JSONObject getJSONFromUrl(String url);

    /**
     * GET
     * @param url String
     * @return JSONObject
     */
    public JSONObject retrieveJSONAsGet(String url, String chipperText);

}
