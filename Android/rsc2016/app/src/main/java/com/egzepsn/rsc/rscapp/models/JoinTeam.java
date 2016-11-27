package com.egzepsn.rsc.rscapp.models;

/**
 * Created by kiki3 on 27.11.2016..
 */

public class JoinTeam {
    private int eventId;
    private int teamId;
    private String password;

    public JoinTeam(int eventId, int teamId, String password) {
        this.eventId = eventId;
        this.teamId = teamId;
        this.password = password;
    }
}
