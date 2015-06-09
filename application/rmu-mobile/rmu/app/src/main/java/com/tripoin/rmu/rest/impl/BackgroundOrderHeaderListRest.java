package com.tripoin.rmu.rest.impl;

import com.tripoin.rmu.model.DTO.order_header.OrderHeaderDTO;
import com.tripoin.rmu.rest.api.IOrderListPost;
import com.tripoin.rmu.rest.base.ARestGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;
import com.tripoin.rmu.view.enumeration.ViewConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Ridla on 4/18/2015.
 */
public abstract class BackgroundOrderHeaderListRest extends ARestGETAsyncTask {

    private IOrderListPost iOrderListPost;

    protected BackgroundOrderHeaderListRest(IOrderListPost iOrderListPost) {
        this.iOrderListPost = iOrderListPost;
    }

    @Override
    public String initUrl() {
        //return RestConstant.TSK_ORDER_HEADER.toString();

        try {
            return RestConstant.TSK_ORDER_HEADER.toString()
                    .concat(ViewConstant.QUESTION.toString())
                    .concat(RestConstant.LAST_VERSION.toString())
                    .concat(ViewConstant.EQUALS.toString())
                    .concat(URLEncoder.encode(getLatestVersion(), RestConstant.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return ViewConstant.EMPTY.toString();
        }
    }

    @Override
    public Class<?> initClassResult() {
        return OrderHeaderDTO.class;
    }

    public abstract String getLatestVersion();

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iOrderListPost.onPostSynchronize(objectResult);
    }
}
