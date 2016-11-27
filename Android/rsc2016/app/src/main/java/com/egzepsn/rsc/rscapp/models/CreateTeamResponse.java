package com.egzepsn.rsc.rscapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class CreateTeamResponse {
    @SerializedName("password")
    private String password;

    @SerializedName("teamId")
    private int teamId;

    @SerializedName("eventId")
    private int eventId;

    public CreateTeamResponse(String password, int teamId, int eventId) {
        this.password = password;
        this.teamId = teamId;
        this.eventId = eventId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
