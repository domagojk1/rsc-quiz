package com.egzepsn.rsc.rscapp.commons.listeners;

/**
 * Created by domagoj on 11/23/16.
 */

public interface FinishedListener {
    void onSuccess();
    void onError(String error);
}
