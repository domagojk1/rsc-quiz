package com.egzepsn.rsc.rscapp.modules.initial.login.interactor;

import com.egzepsn.rsc.rscapp.commons.listeners.FinishedListener;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public interface LoginInteractor {
    void login(FinishedListener listener, CallbackManager manager, LoginButton button);
}
