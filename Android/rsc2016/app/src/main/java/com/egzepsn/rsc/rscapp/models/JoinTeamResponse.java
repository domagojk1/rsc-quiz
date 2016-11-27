package com.egzepsn.rsc.rscapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class JoinTeamResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
