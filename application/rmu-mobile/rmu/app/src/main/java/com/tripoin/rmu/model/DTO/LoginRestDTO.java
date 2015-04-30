package com.tripoin.rmu.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tripoin.rmu.model.base.impl.BaseRESTDTO;

/**
 * Created by Achmad Fauzi on 2/11/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class LoginRestDTO extends BaseRESTDTO {

    @JsonProperty("password_expired_date")
    private String passwordExpiredDate;

    public String getPasswordExpiredDate() {
        return passwordExpiredDate;
    }

    public void setPasswordExpiredDate(String passwordExpiredDate) {
        this.passwordExpiredDate = passwordExpiredDate;
    }

    @Override
    public String toString() {
        return "LoginRestDTO{" +
                "passwordExpiredDate='" + passwordExpiredDate + '\'' +
                '}';
    }
}
