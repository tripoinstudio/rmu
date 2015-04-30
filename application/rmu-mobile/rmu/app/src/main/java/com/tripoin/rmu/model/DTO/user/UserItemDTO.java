package com.tripoin.rmu.model.DTO.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Ridla on 4/18/2015.
 */
public class UserItemDTO /*implements Parcelable*/ implements Serializable{

    @JsonProperty("user_code")
    private String userCode;

    @JsonProperty("role_code")
    private String roleCode;

    /*public UserItemDTO(Parcel in) {
        readFromParcel(in);
    }*/

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        return "UserItemDTO{" +
                "userCode='" + userCode + '\'' +
                ", roleCode='" + roleCode + '\'' +
                '}';
    }

    /*@Override
    public int describeContents() {
        return 0;
    }*/

    /*@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userCode);
        dest.writeString(roleCode);
    }*/

    /*private void readFromParcel(Parcel in) {
        userCode = in.readString();
        roleCode = in.readString();
    }*/

    /*Creator creator = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new UserItemDTO(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new UserItemDTO[size];
        }
    };*/
}
