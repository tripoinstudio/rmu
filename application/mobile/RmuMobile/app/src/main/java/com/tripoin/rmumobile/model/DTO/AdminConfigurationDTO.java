package com.tripoin.rmumobile.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Achmad Fauzi on 12/24/2014.
 * fauzi.knightmaster.achmad@gmail.com
 * Holding Admin Config Json from server to Mobile device
 */
public class AdminConfigurationDTO extends BaseRESTDTO implements Serializable{

    @JsonProperty("interval")
    private String interval;

    @JsonProperty("video_id")
    private ArrayList<String> videoId;

    @JsonProperty("url")
    private ArrayList<String> url;

    @JsonProperty("server_host")
    private String serverHost;

    @JsonProperty("server_port")
    private String serverPort;

    @JsonProperty("start_working_hour")
    private String startWorkingHour;

    @JsonProperty("stop_working_hour")
    private String stopWorkingHour;


    @JsonProperty("video_resolution")
    private ArrayList<String> video_resolution;

    @JsonProperty("latency")
    private String latency;

    @JsonProperty("download")
    private String download;

    @JsonProperty("upload")
    private String upload;

    @JsonProperty("video_view_url")
    private ArrayList<String> videoview;

    public ArrayList<String> getVideoview() {
        return videoview;
    }

    public void setVideoview(ArrayList<String> videoview) {
        this.videoview = videoview;
    }

    public ArrayList<String> getVideo_resolution() {
        return video_resolution;
    }

    public void setVideo_resolution(ArrayList<String> video_resolution) {
        this.video_resolution = video_resolution;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getStartWorkingHour() {
        return startWorkingHour;
    }

    public void setStartWorkingHour(String startWorkingHour) {
        this.startWorkingHour = startWorkingHour;
    }

    public String getStopWorkingHour() {
        return stopWorkingHour;
    }

    public void setStopWorkingHour(String stopWorkingHour) {
        this.stopWorkingHour = stopWorkingHour;
    }

    public String getLatency() {
        return latency;
    }

    public void setLatency(String latency) {
        this.latency = latency;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public ArrayList<String> getVideoId() {
        return videoId;
    }

    public void setVideoId(ArrayList<String> videoId) {
        this.videoId = videoId;
    }

    public ArrayList<String> getUrl() {
        return url;
    }

    public void setUrl(ArrayList<String> url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "AdminConfigurationDTO{" +
                "interval='" + interval + '\'' +
                ", videoId=" + videoId +
                ", url=" + url +
                ", serverHost='" + serverHost + '\'' +
                ", serverPort='" + serverPort + '\'' +
                ", startWorkingHour='" + startWorkingHour + '\'' +
                ", stopWorkingHour='" + stopWorkingHour + '\'' +
                ", video_resolution=" + video_resolution +
                ", latency='" + latency + '\'' +
                ", download='" + download + '\'' +
                ", upload='" + upload + '\'' +
                ", videoview='" + videoview + '\'' +
                '}';
    }
}
