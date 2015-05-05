package com.tripoin.rmu.rest.api;

import android.content.Context;

/**
 * Created by Achmad Fauzi on 5/4/2015 : 10:21 AM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public interface IBaseRest {

    public Context getContext();

    public String initUrl();

    public Class<?> initClassResult();

    public String getServerHost();

    public String getServerPort();

    public Object getObjectResult();

    public String constructBaseURL();

    public String processedURL();
}
