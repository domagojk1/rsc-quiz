package com.egzepsn.rsc.rscapp.signalr;

/**
 * Created by kiki3 on 27.11.2016..
 */

public interface SignalRListener {
    void onHubMessageReceived(Object object);
    void onHubMessageReceivedStartQuiz(Object object);
}