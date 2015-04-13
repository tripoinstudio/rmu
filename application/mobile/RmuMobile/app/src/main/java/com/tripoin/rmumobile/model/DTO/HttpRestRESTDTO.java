package com.tripoin.rmumobile.model.DTO;

import java.io.Serializable;

import id.co.telkomsigma.ariumm_force.model.top.ABaseRESTDTO;

/**
 * Created by Achmad Fauzi on 11/19/2014.
 * achmad.fauzi@sigma.co.id
 *
 * Class DTO for  basic Rest
 */
public class HttpRestRESTDTO extends ABaseRESTDTO implements Serializable{

    private String url;
    private String resultValue;
    private String resultKey;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }

    public String getResultKey() {
        return resultKey;
    }

    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HttpRestRESTDTO)) return false;

        HttpRestRESTDTO that = (HttpRestRESTDTO) o;

        if (!resultKey.equals(that.resultKey)) return false;
        if (!resultValue.equals(that.resultValue)) return false;
        if (!url.equals(that.url)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + resultValue.hashCode();
        result = 31 * result + resultKey.hashCode();
        return result;
    }
}
