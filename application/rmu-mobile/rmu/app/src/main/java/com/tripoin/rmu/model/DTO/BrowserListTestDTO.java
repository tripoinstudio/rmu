package com.tripoin.rmu.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achmad Fauzi on 12/31/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class BrowserListTestDTO implements Serializable{

    private List<String> loadingStatus = new ArrayList<String>();
    private List<String> loadingTime = new ArrayList<String>();
    private List<String> throughputs = new ArrayList<String>();
    private List<String> urls = new ArrayList<String>();
    private List<String> urlResults = new ArrayList<String>();
    private List<String> webSize = new ArrayList<String>();
    private List<String> dateTime = new ArrayList<String>();
    private int increment;
    private String testId;
    private String startTest;
    private double trafficBefore;
    private double trafficAfter;

    public double getTrafficTotal() {
        return trafficTotal;
    }

    public void setTrafficTotal(double trafficTotal) {
        this.trafficTotal = trafficTotal;
    }

    public double getTrafficAfter() {
        return trafficAfter;
    }

    public void setTrafficAfter(double trafficAfter) {
        this.trafficAfter = trafficAfter;
    }

    private double trafficTotal;

    public double getTrafficBefore() {
        return trafficBefore;
    }

    public void setTrafficBefore(double trafficBefore) {
        this.trafficBefore = trafficBefore;
    }

    public List<String> getLoadingStatus() {
        return loadingStatus;
    }

    public void setLoadingStatus(List<String> loadingStatus) {
        this.loadingStatus = loadingStatus;
    }

    public List<String> getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(List<String> loadingTime) {
        this.loadingTime = loadingTime;
    }

    public List<String> getThroughputs() {
        return throughputs;
    }

    public void setThroughputs(List<String> throughputs) {
        this.throughputs = throughputs;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public List<String> getUrlResults() {
        return urlResults;
    }

    public void setUrlResults(List<String> urlResults) {
        this.urlResults = urlResults;
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

    public List<String> getWebSize() {
        return webSize;
    }

    public void setWebSize(List<String> webSize) {
        this.webSize = webSize;
    }
}