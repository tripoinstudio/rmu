package com.tripoin.rmumobile.model.DTO;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 12/1/2014.
 * fauzi.knightmaster.achmad@gmail.com
 *
 * Class Data Transfer Object for Phone and Network Monitor
 */
public class PhoneNetworkDTO implements Serializable{

    private String latitude;
    private String longitude;
    private String accuracy;
    private String deviceId;
    private String subscriberId;
    private String phoneType;
    private String softwareVersion;
    private String simOperatorName;
    private String simSerial;
    private String mcc;
    private String mnc;
    private String linenumber;
    private String batterylevel;
    private String locality;
    private String addressline;
    private String signalstrength;
    private String signalquality;
    private String ber;
    private String networktype;
    private String wifiName;
    private String lac;
    private String ci;
    private String connectivityStatus;
    private String phonestate;
    private String ipAddress;
    private String user;
    private String apn;

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public String getSimSerial() {
        return simSerial;
    }

    public void setSimSerial(String simSerial) {
        this.simSerial = simSerial;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(String linenumber) {
        this.linenumber = linenumber;
    }

    public String getBatterylevel() {
        return batterylevel;
    }

    public void setBatterylevel(String batterylevel) {
        this.batterylevel = batterylevel;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddressline() {
        return addressline;
    }

    public void setAddressline(String addressline) {
        this.addressline = addressline;
    }

    public String getSignalstrength() {
        return signalstrength;
    }

    public void setSignalstrength(String signalstrength) {
        this.signalstrength = signalstrength;
    }

    public String getSignalquality() {
        return signalquality;
    }

    public void setSignalquality(String signalquality) {
        this.signalquality = signalquality;
    }

    public String getBer() {
        return ber;
    }

    public void setBer(String ber) {
        this.ber = ber;
    }

    public String getNetworktype() {
        return networktype;
    }

    public void setNetworktype(String networktype) {
        this.networktype = networktype;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getPhonestate() {
        return phonestate;
    }

    public void setPhonestate(String phonestate) {
        this.phonestate = phonestate;
    }

    public String getConnectivityStatus() {
        return connectivityStatus;
    }

    public void setConnectivityStatus(String connectivityStatus) {
        this.connectivityStatus = connectivityStatus;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PhoneNetworkDTO{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", subscriberId='" + subscriberId + '\'' +
                ", phoneType='" + phoneType + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", simOperatorName='" + simOperatorName + '\'' +
                ", simSerial='" + simSerial + '\'' +
                ", mcc='" + mcc + '\'' +
                ", mnc='" + mnc + '\'' +
                ", linenumber='" + linenumber + '\'' +
                ", batterylevel='" + batterylevel + '\'' +
                ", locality='" + locality + '\'' +
                ", addressline='" + addressline + '\'' +
                ", signalstrength='" + signalstrength + '\'' +
                ", signalquality='" + signalquality + '\'' +
                ", ber='" + ber + '\'' +
                ", networktype='" + networktype + '\'' +
                ", wifiName='" + wifiName + '\'' +
                ", lac='" + lac + '\'' +
                ", ci='" + ci + '\'' +
                ", connectivityStatus='" + connectivityStatus + '\'' +
                ", phonestate='" + phonestate + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", user='" + user + '\'' +
                ", apn='" + apn + '\'' +
                '}';
    }
}
