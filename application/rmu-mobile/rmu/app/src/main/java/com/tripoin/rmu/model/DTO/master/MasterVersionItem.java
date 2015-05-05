package com.tripoin.rmu.model.DTO.master;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Achmad Fauzi on 4/29/2015 : 10:04 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class MasterVersionItem implements Parcelable{

    @JsonProperty("version_table")
    private String versionTable;

    @JsonProperty("version_timestamp")
    private String versionTimeStamp;

    public MasterVersionItem() {
    }

    public MasterVersionItem(Parcel in) {
        readFromParcel(in);
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in){
        versionTable = in.readString();
        versionTimeStamp = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(versionTable);
        dest.writeString(versionTimeStamp);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MasterVersionItem createFromParcel(Parcel in) {
            return new MasterVersionItem(in);
        }
        public MasterVersionItem[] newArray(int size) {
            return new MasterVersionItem[size];
        }
    };
}
