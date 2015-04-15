package com.tripoin.rmumobile.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achmad Fauzi on 1/24/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class SpeedTestDTO implements Serializable{

    private List<String> batchIds = new ArrayList<String>();
    private Double latency;
    private Double download;
    private Double upload;

    public List<String> getBatchIds() {
        return batchIds;
    }

    public void setBatchIds(List<String> batchIds) {
        this.batchIds = batchIds;
    }

    public Double getLatency() {
        return latency;
    }

    public void setLatency(Double latency) {
        this.latency = latency;
    }

    public Double getDownload() {
        return download;
    }

    public void setDownload(Double download) {
        this.download = download;
    }

    public Double getUpload() {
        return upload;
    }

    public void setUpload(Double upload) {
        this.upload = upload;
    }

    @Override
    public String toString() {
        return "SpeedTestDTO{" +
                "batchIds=" + batchIds +
                ", latency=" + latency +
                ", download=" + download +
                ", upload=" + upload +
                '}';
    }
}
