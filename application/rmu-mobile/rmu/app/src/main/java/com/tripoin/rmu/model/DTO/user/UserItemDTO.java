package com.tripoin.rmu.model.DTO.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Ridla on 4/18/2015.
 */
public class UserItemDTO {

    @JsonProperty("user_code")
    private String userCode;

    @JsonProperty("role_code")
    private String roleCode;

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
}
