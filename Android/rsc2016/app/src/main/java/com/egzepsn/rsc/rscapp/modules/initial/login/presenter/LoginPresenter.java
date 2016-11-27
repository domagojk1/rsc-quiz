package com.egzepsn.rsc.rscapp.modules.initial.login.presenter;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public interface LoginPresenter {
    void loginFacebook(CallbackManager callbackManager, LoginButton button);
}
