package com.tripoin.rmu.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dian on 22/01/2015.
 */
public class VideoViewListDTO implements Serializable {

    private int increment;

    private List<String> batchIds = new ArrayList<String>();

    private List<String> vUrl = new ArrayList<String>();
    private List<String> vUrls = new ArrayList<String>();
    private List<String> vEvents = new ArrayList <String>();
    private List<String> vStatus = new ArrayList <String>();
    private List<String> vInitialBuffer = new ArrayList <String>();
    private List<String> vNumbOfBuffer = new ArrayList <String>();
    private List<String> vRebufferingTime = new ArrayList <String>();
    private List<String> vTotalTimeBuffer = new ArrayList <String>();
    private List<String> vResolution = new ArrayList <String>();
    private List<String> vDuration = new ArrayList <String>();
    private List<String> vThroughput = new ArrayList <String>();
    private List<String> dateTime = new ArrayList<String>();
    private String startTest;
    private String testId;

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public List<String> getBatchIds() {
        return batchIds;
    }

    public void setBatchIds(List<String> batchIds) {
        this.batchIds = batchIds;
    }

    public List<String> getvUrl() {
        return vUrl;
    }

    public void setvUrl(List<String> vTitles) {
        this.vUrl = vTitles;
    }

    public List<String> getvUrls() {
        return vUrls;
    }

    public void setvUrls(List<String> vUrls) {
        this.vUrls = vUrls;
    }

    public List<String> getvEvents() {
        return vEvents;
    }

    public void setvEvents(List<String> vEvents) {
        this.vEvents = vEvents;
    }

    public List<String> getvStatus() {
        return vStatus;
    }

    public void setvStatus(List<String> vStatus) {
        this.vStatus = vStatus;
    }

    public List<String> getvInitialBuffer() {
        return vInitialBuffer;
    }

    public void setvInitialBuffer(List<String> vInitialBuffer) {
        this.vInitialBuffer = vInitialBuffer;
    }

    public List<String> getvNumbOfBuffer() {
        return vNumbOfBuffer;
    }

    public void setvNumbOfBuffer(List<String> vNumbOfBuffer) {
        this.vNumbOfBuffer = vNumbOfBuffer;
    }

    public List<String> getvRebufferingTime() {
        return vRebufferingTime;
    }

    public void setvRebufferingTime(List<String> vRebufferingTime) {
        this.vRebufferingTime = vRebufferingTime;
    }

    public List<String> getvTotalTimeBuffer() {
        return vTotalTimeBuffer;
    }

    public void setvTotalTimeBuffer(List<String> vTotalTimeBuffer) {
        this.vTotalTimeBuffer = vTotalTimeBuffer;
    }

    public List<String> getvResolution() {
        return vResolution;
    }

    public void setvResolution(List<String> vResolution) {
        this.vResolution = vResolution;
    }

    public List<String> getvDuration() {
        return vDuration;
    }

    public void setvDuration(List<String> vDuration) {
        this.vDuration = vDuration;
    }

    public List<String> getvThroughput() {
        return vThroughput;
    }

    public void setvThroughput(List<String> vThroughput) {
        this.vThroughput = vThroughput;
    }

    public List<String> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<String> dateTime) {
        this.dateTime = dateTime;
    }

    public String getStartTest() {
        return startTest;
    }

    public void setStartTest(String startTest) {
        this.startTest = startTest;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "VideoViewListDTO{" +
                "increment=" + increment +
                ", batchIds=" + batchIds +
                ", vUrl=" + vUrl +
                ", vUrls=" + vUrls +
                ", vEvents=" + vEvents +
                ", vStatus=" + vStatus +
                ", vInitialBuffer=" + vInitialBuffer +
                ", vNumbOfBuffer=" + vNumbOfBuffer +
                ", vRebufferingTime=" + vRebufferingTime +
                ", vTotalTimeBuffer=" + vTotalTimeBuffer +
                ", vResolution=" + vResolution +
                ", vDuration=" + vDuration +
                ", vThroughput=" + vThroughput +
                ", dateTime=" + dateTime +
                ", startTest='" + startTest + '\'' +
                ", testId='" + testId + '\'' +
                '}';
    }
}
