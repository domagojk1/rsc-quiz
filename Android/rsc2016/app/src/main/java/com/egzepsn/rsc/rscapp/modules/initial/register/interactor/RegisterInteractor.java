package com.egzepsn.rsc.rscapp.modules.initial.register.interactor;

import com.egzepsn.rsc.rscapp.commons.listeners.FinishedListener;

/**
 * Created by domagoj on 11/23/16.
 */

public interface RegisterInteractor {
    void register(String username, String password, String confirmPassword, FinishedListener listener);
}
