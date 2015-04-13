package com.tripoin.rmumobile.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Achmad Fauzi on 11/25/2014.
 * achmad.fauzi@sigma.co.id
 *
 * Base Class for Rest DTO result
 */

public abstract class ABaseRESTDTO {

    @JsonProperty("err_code")
    private String err_code;

    @JsonProperty("err_msg")
    private String err_msg;

    @JsonProperty("RESULT")
    private String result;

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
