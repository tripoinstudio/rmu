package com.tripoin.rmu.model.DTO;

import java.io.Serializable;

/**
 * Created by Dian on 30/12/2014.
 */
public class DropCallDTO implements Serializable {

    private int id;
    private String pn;
    private String date_time;
    private String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "DropCallDTO{" +
                "id=" + id +
                ", pn='" + pn + '\'' +
                ", date_time='" + date_time + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

}
