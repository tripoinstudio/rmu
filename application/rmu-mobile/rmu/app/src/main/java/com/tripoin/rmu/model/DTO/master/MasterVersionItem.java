package com.tripoin.rmu.model.DTO.master;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Achmad Fauzi on 4/29/2015 : 10:04 PM.
 * mailto : achmad.fauzi@sigma.co.id
 */
public class MasterVersionItem {

    @JsonProperty("version_table")
    private String versionTable;

    @JsonProperty("version_timestamp")
    private String versionTimeStamp;

    public String getVersionTable() {
        return versionTable;
    }

    public void setVersionTable(String versionTable) {
        this.versionTable = versionTable;
    }

    public String getVersionTimeStamp() {
        return versionTimeStamp;
    }

    public void setVersionTimeStamp(String versionTimeStamp) {
        this.versionTimeStamp = versionTimeStamp;
    }

    @Override
    public String toString() {
        return "MasterVersion{" +
                "versionTable='" + versionTable + '\'' +
                ", versionTimeStamp='" + versionTimeStamp + '\'' +
                '}';
    }
}
