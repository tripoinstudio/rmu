package com.tripoin.rmumobile.util.top;

import android.content.Context;

import com.tripoin.rmumobile.util.api.IPropertyUtil;

/**
 * Created by Achmad Fauzi on 11/29/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 *
 */
public abstract class APropertyUtil extends APropertyUtilBase implements IPropertyUtil {

    protected APropertyUtil(String fileName, Context context) {
        super(fileName, context);
    }
}
