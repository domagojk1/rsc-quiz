package com.egzepsn.rsc.rscapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class Event implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("isOpen")
    private boolean isOpen;

    @SerializedName("place")
    private String name;

    @SerializedName("dateTime")
    private String dateTime;

    @SerializedName("description")
    private String description;

    @SerializedName("teams")
    private ArrayList<Team> teams;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
