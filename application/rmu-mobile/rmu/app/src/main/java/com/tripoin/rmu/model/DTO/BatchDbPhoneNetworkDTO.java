package com.tripoin.rmu.model.DTO;

/**
 * Created by Achmad Fauzi on 12/22/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class BatchDbPhoneNetworkDTO {

    private Integer batchId;
    private String wsDraftUrl;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getWsDraftUrl() {
        return wsDraftUrl;
    }

    public void setWsDraftUrl(String wsDraftUrl) {
        this.wsDraftUrl = wsDraftUrl;
    }

    @Override
    public String toString() {
        return "BatchDbPhoneNetworkDTO{" +
                "batchId=" + batchId +
                ", wsDraftUrl='" + wsDraftUrl + '\'' +
                '}';
    }
}
