package com.tripoin.rmu.model.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achmad Fauzi on 12/29/2014.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class VideoListTestDTO implements Serializable {

    private int increment;

    private List<String> batchIds = new ArrayList<String>();
    private List<String> titles = new ArrayList<String>();
    private List<String> durations = new ArrayList<String>();
    private List<String> ids = new ArrayList<String>();
    private List<String> definitions = new ArrayList<String>();

    private List<String> tBuffEvt = new ArrayList<String>();
    private List<String> tBuffTime = new ArrayList<String>();
    private List<String> vInitBuff = new ArrayList<String>();
    private List<String> vRebuff = new ArrayList<String>();
    private List<String> vId = new ArrayList<String>();
    private List<String> vDur = new ArrayList<String>();
    private List<String> vThroughput = new ArrayList<String>();
    private List<String> vDefinitions= new ArrayList<String>();
    private List<String> vResolutions= new ArrayList<String>();
    private List<String> dateTime = new ArrayList<String>();

    public List<String> gettBuffEvt() {
        return tBuffEvt;
    }

    public void settBuffEvt(List<String> tBuffEvt) {
        this.tBuffEvt = tBuffEvt;
    }

    public List<String> gettBuffTime() {
        return tBuffTime;
    }

    public void settBuffTime(List<String> tBuffTime) {
        this.tBuffTime = tBuffTime;
    }

    public List<String> getvId() {
        return vId;
    }

    public void setvId(List<String> vId) {
        this.vId = vId;
    }

    public List<String> getvDur() {
        return vDur;
    }

    public void setvDur(List<String> vDur) {
        this.vDur = vDur;
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

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public List<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<String> definitions) {
        this.definitions = definitions;
    }

    public List<String> getvDefinitions() {
        return vDefinitions;
    }

    public void setvDefinitions(List<String> vDefinitions) {
        this.vDefinitions = vDefinitions;
    }

    public List<String> getBatchIds() {
        return batchIds;
    }

    public void setBatchIds(List<String> batchIds) {
        this.batchIds = batchIds;
    }

    public List<String> getvResolutions() {
        return vResolutions;
    }

    public void setvResolutions(List<String> vResolutions) {
        this.vResolutions = vResolutions;
    }

    public List<String> getvInitBuff() {
        return vInitBuff;
    }

    public void setvInitBuff(List<String> vInitBuff) {
        this.vInitBuff = vInitBuff;
    }

    public List<String> getvRebuff() {
        return vRebuff;
    }

    public void setvRebuff(List<String> vRebuff) {
        this.vRebuff = vRebuff;
    }

    @Override
    public String toString() {
        return "VideoListTestDTO{" +
                "increment=" + increment +
                ", batchIds=" + batchIds +
                ", titles=" + titles +
                ", durations=" + durations +
                ", ids=" + ids +
                ", definitions=" + definitions +
                ", tBuffEvt=" + tBuffEvt +
                ", tBuffTime=" + tBuffTime +
                ", vInitBuff=" + vInitBuff +
                ", vRebuff=" + vRebuff +
                ", vId=" + vId +
                ", vDur=" + vDur +
                ", vThroughput=" + vThroughput +
                ", vDefinitions=" + vDefinitions +
                ", vResolutions=" + vResolutions +
                ", dateTime=" + dateTime +
                '}';
    }
}
