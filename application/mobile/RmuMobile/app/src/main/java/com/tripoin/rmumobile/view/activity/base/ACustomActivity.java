package com.tripoin.rmumobile.view.activity.base;

import android.widget.EditText;
import android.widget.TextView;

import com.tripoin.rmumobile.R;
import com.tripoin.rmumobile.rest.URLBuilder;
import com.tripoin.rmumobile.util.NetworkConnectivity;
import com.tripoin.rmumobile.util.enumeration.PropertyConstant;
import com.tripoin.rmumobile.util.impl.PropertyUtil;
import com.tripoin.rmumobile.view.activity.api.ICustomActivity;
import com.tripoin.rmumobile.view.activity.base.ABaseActivity;
import com.tripoin.rmumobile.view.enumeration.ViewConstant;

import java.util.List;

import roboguice.inject.InjectResource;

/**
 * Created by Achmad Fauzi on 11/19/2014.
 * achmad.fauzi@sigma.co.id
 *
 * This class is used for Activity which need capability to manipulate property file
 */
public abstract class ACustomActivity extends ABaseActivity implements ICustomActivity {

    public PropertyUtil propertyUtil;
    public PropertyUtil securityUtil;
    public URLBuilder urlBuilder = new URLBuilder( generalConverter );
    public NetworkConnectivity networkConnectivity;

    @InjectResource( R.string.app_name ) protected String appName;

    protected ACustomActivity() {
        propertyUtil = new PropertyUtil(PropertyConstant.PROPERTY_FILE_NAME.toString(), this);
        securityUtil = new PropertyUtil( PropertyConstant.LOGIN_FILE_NAME.toString(), this );
        networkConnectivity = new NetworkConnectivity( this, null );
    }

    @Override
    protected String[] initAssetName() {
        return new String[]{
                ViewConstant.FONT_ADAM.toString(),
                ViewConstant.FONT_OPEN_SANS_LIGHT.toString(),
                ViewConstant.FONT_OPEN_SANS_LIGHT.toString()
        };
    }

    @Override
    protected List<TextView> getTitleTextViews() {
        return null;
    }

    @Override
    protected List<TextView> getContentTextViews() {
        return null;
    }

    @Override
    protected List<EditText> getEditTexts() {
        return null;
    }

    public void updateProperties(){
        propertyUtil = new PropertyUtil(PropertyConstant.PROPERTY_FILE_NAME.toString(), this );
        securityUtil = new PropertyUtil( PropertyConstant.LOGIN_FILE_NAME.toString(), this );
    }

}
