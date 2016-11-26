package com.egzepsn.rsc.rscapp.modules.initial.register.presenter;

import com.egzepsn.rsc.rscapp.modules.initial.register.interactor.RegisterInteractor;
import com.egzepsn.rsc.rscapp.modules.initial.register.interactor.RegisterInteractorImpl;
import com.egzepsn.rsc.rscapp.modules.initial.register.view.RegisterView;
import com.egzepsn.rsc.rscapp.commons.listeners.FinishedListener;

import java.lang.ref.WeakReference;

/**
 * Created by domagoj on 11/23/16.
 */

public class RegisterPresenterImpl implements RegisterPresenter, FinishedListener {
    private WeakReference<RegisterView> view;
    private RegisterInteractor interactor;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = new WeakReference<RegisterView>(view);
        this.interactor = new RegisterInteractorImpl();
    }

    @Override
    public void register(String username, String password, String confirmPassword) {
        view.get().showLoadingIndicator();
        interactor.register(username, password, confirmPassword, this);
    }

    @Override
    public void onSuccess() {
        view.get().hideLoadingIndicator();
        view.get().navigateToLogin();
    }

    @Override
    public void onError(String error) {
        view.get().hideLoadingIndicator();
        view.get().showError(error);
    }
}
