package com.tripoin.rmumobile.model.DTO;

import java.io.Serializable;

/**
 * Created by Achmad Fauzi on 2/13/2015.
 * fauzi.knightmaster.achmad@gmail.com
 */
public class PasswordExpiredDTO implements Serializable{

    private int diffDays;

    private boolean isExpired;

    public int getDiffDays() {
        return diffDays;
    }

    public void setDiffDays(int diffDays) {
        this.diffDays = diffDays;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    @Override
    public String toString() {
        return "PasswordExpiredDTO{" +
                "diffDays=" + diffDays +
                ", isExpired=" + isExpired +
                '}';
    }
}
