package com.egzepsn.rsc.rscapp.modules.initial.login.presenter;

import android.util.Log;

import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.enums.AppStateEnum;
import com.egzepsn.rsc.rscapp.modules.initial.login.interactor.LoginInteractor;
import com.egzepsn.rsc.rscapp.modules.initial.login.interactor.LoginInteractorImpl;
import com.egzepsn.rsc.rscapp.modules.initial.login.view.LoginView;
import com.egzepsn.rsc.rscapp.commons.listeners.FinishedListener;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import java.lang.ref.WeakReference;

public class LoginPresenterImpl implements LoginPresenter, FinishedListener {
    private WeakReference<LoginView> view;
    private LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view){
        this.view = new WeakReference<LoginView>(view);
        this.interactor = new LoginInteractorImpl();
    }

    @Override
    public void loginFacebook(CallbackManager callbackManager, LoginButton button) {
        view.get().resetMessage();
        RSCApp.getInstance().setAppState(AppStateEnum.LoggingIn);
        interactor.loginFacebook(this, callbackManager, button);
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onSuccess() {
        Log.e("ONSUCCESS", "PRESENTERIMPL");
        view.get().hideLoadingIndicator();
        view.get().navigateToMain();
    }

    @Override
    public void onError(String error) {
        view.get().hideLoadingIndicator();
        view.get().showError(error);
    }
}
