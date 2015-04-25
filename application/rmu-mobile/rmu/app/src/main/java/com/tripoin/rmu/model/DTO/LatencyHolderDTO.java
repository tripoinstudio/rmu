package com.tripoin.rmu.model.DTO;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 1/21/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class LatencyHolderDTO implements Serializable{

    private String responseTime;
    private String err_message;

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getErr_message() {
        return err_message;
    }

    public void setErr_message(String err_message) {
        this.err_message = err_message;
    }

    @Override
    public String toString() {
        return "LatencyDTO{" +
                "responseTime='" + responseTime + '\'' +
                ", err_message='" + err_message + '\'' +
                '}';
    }
}
