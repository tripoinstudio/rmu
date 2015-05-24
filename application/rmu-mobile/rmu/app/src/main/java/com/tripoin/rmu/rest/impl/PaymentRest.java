package com.tripoin.rmu.rest.impl;

import android.util.Log;

import com.tripoin.rmu.model.DTO.menu.MenuDTO;
import com.tripoin.rmu.model.DTO.order_detail.OrderDetailDTO;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;
import com.tripoin.rmu.rest.api.IMenuPost;
import com.tripoin.rmu.rest.api.IPaymentPost;
import com.tripoin.rmu.rest.base.ARestDialogGETAsyncTask;
import com.tripoin.rmu.rest.enumeration.RestConstant;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Ridla on 4/18/2015.
 */
public abstract class PaymentRest extends ARestDialogGETAsyncTask{

    private IPaymentPost iPaymentPost;
    private String statusOrder;

    protected PaymentRest(IPaymentPost iPaymentPost, String statusOrder) {
        this.iPaymentPost = iPaymentPost;
        this.statusOrder = statusOrder;
    }

    protected PaymentRest(IPaymentPost iPaymentPost) {
        this.iPaymentPost = iPaymentPost;
    }

    @Override
    public String initUrl() {
        String paymentData = null;
        try {
            Log.d("Payment Data ", getPaymentData());
            paymentData = URLEncoder.encode(getPaymentData(), RestConstant.URL_ENCODER.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return RestConstant.TSK_PAYMENT.toString().concat("?").concat(RestConstant.TSK_PAYMENT_PARAM.toString()).concat("=").concat(paymentData);
    }

    @Override
    public Class<?> initClassResult() {
        return OrderDetailDTO.class;
    }


    @Override
    protected String getProgressDialogTitle() {
        return "Processing Payment";
    }
    
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        iPaymentPost.onPostSyncPayment(objectResult);
    }

    public String getStatusOrder(){
        return this.statusOrder;
    }

    public abstract String getPaymentData();
}
