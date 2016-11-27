package com.egzepsn.rsc.rscapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class CreateTeam {
    @SerializedName("name")
    private String name;

    @SerializedName("eventId")
    private int eventId;

    public CreateTeam(String name, int eventId) {
        this.name = name;
        this.eventId = eventId;
    }
}
