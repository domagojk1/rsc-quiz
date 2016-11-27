package com.egzepsn.rsc.rscapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mario on 24.11.2016..
 */
public class ErrorField  implements Serializable {
    @SerializedName("message")
    private String message;

    @SerializedName("field")
    private String field;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
