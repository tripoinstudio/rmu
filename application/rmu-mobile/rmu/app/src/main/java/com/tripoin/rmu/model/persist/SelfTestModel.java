package com.tripoin.rmu.model.persist;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.tripoin.rmu.model.api.ModelConstant;


/**
 * Created by Achmad Fauzi on 1/26/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */

@DatabaseTable( tableName = ModelConstant.SELF_TEST_TABLE )
public class SelfTestModel {

    @DatabaseField( generatedId = true, canBeNull = false, columnName = ModelConstant.SELF_TEST_ID )
    private int id;

    @DatabaseField( columnName = ModelConstant.RETRY_NUMBER )
    private int retryNumber;

    @DatabaseField( columnName = ModelConstant.ONE_CLICK_TEST_ID )
    private String testId;

    @DatabaseField( columnName = ModelConstant.START_ONE_CLICK_TEST )
    private String startTest;

    @DatabaseField( columnName = ModelConstant.TEST_TYPE )
    private String testType;

    @DatabaseField( columnName = ModelConstant.DEVICE_ID )
    private String deviceId;

    @DatabaseField( columnName = ModelConstant.USER_CODE )
    private String userCode;

    @DatabaseField( columnName = ModelConstant.TEST_STATUS )
    private String testStatus;

    @DatabaseField( columnName = ModelConstant.REASON )
    private String reason;

    @DatabaseField( columnName = ModelConstant.LATENCY_TABLE )
    private int latency;

    @DatabaseField( columnName = ModelConstant.DOWNLOAD_TABLE )
    private int download;

    @DatabaseField( columnName =  ModelConstant.UPLOAD_TABLE )
    private int upload;

    @DatabaseField( columnName = ModelConstant.YST_TABLE)
    private int yst;

    @DatabaseField( columnName = ModelConstant.BST_TABLE )
    private int bst;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLatency() {
        return latency;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }

    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public int getUpload() {
        return upload;
    }

    public void setUpload(int upload) {
        this.upload = upload;
    }

    public int getYst() {
        return yst;
    }

    public void setYst(int yst) {
        this.yst = yst;
    }

    public int getBst() {
        return bst;
    }

    public void setBst(int bst) {
        this.bst = bst;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getStartTest() {
        return startTest;
    }

    public void setStartTest(String startTest) {
        this.startTest = startTest;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getRetryNumber() {
        return retryNumber;
    }

    public void setRetryNumber(int retryNumber) {
        this.retryNumber = retryNumber;
    }

    @Override
    public String toString() {
        return "SelfTestModel{" +
                "id=" + id +
                ", retryNumber=" + retryNumber +
                ", testId='" + testId + '\'' +
                ", startTest='" + startTest + '\'' +
                ", testType='" + testType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", userCode='" + userCode + '\'' +
                ", testStatus='" + testStatus + '\'' +
                ", reason='" + reason + '\'' +
                ", latency=" + latency +
                ", download=" + download +
                ", upload=" + upload +
                ", yst=" + yst +
                ", bst=" + bst +
                '}';
    }
}
