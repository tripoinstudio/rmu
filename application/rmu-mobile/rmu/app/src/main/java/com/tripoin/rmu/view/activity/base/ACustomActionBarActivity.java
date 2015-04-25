package com.tripoin.rmu.view.activity.base;

import com.tripoin.rmu.util.impl.PropertyUtil;

import roboguice.activity.RoboActivity;

/**
 * Created by Achmad Fauzi on 11/25/2014.
 * achmad.fauzi@sigma.co.id
 *
 *
 */
public abstract class ACustomActionBarActivity extends RoboActivity{

    protected PropertyUtil propertyUtil;

    protected ACustomActionBarActivity(){
        propertyUtil = new PropertyUtil( initPropertyName(), this );
    }

    public abstract String initPropertyName();

    public abstract int initPropertySize();


}
