package com.tripoin.rmu.model.DTO;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 1/21/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class DownloadHolderDTO implements Serializable {

    private int size;
    private String fileName;
    private Double totalTime;
    private String type;
    private String errMessage;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    @Override
    public String toString() {
        return "DownloadHolderDTO{" +
                "size=" + size +
                ", fileName='" + fileName + '\'' +
                ", totalTime=" + totalTime +
                ", type='" + type + '\'' +
                '}';
    }
}
