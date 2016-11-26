package com.egzepsn.rsc.rscapp.models;

/**
 * Created by kiki3 on 26.11.2016..
 */

public class Event {
    private boolean enabled;
    private String name;
    private String time;
    private String quizDescription;

    public Event(boolean enabled, String name, String time, String quizDescription) {
        this.enabled = enabled;
        this.name = name;
        this.time = time;
        this.quizDescription = quizDescription;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }
}
