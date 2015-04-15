package com.tripoin.rmumobile.model.DTO;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 1/22/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class UploadHolderDTO implements Serializable{

    private Long elapsedTime;
    private String errMsg;

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "UploadHolderDTO{" +
                "elapsedTime=" + elapsedTime +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
