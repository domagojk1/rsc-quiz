package com.egzepsn.rsc.rscapp.models;

import java.util.ArrayList;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class Team {
    private String name;
    private ArrayList<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
