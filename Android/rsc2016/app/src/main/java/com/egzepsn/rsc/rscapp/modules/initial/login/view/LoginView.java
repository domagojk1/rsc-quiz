package com.egzepsn.rsc.rscapp.modules.initial.login.view;

public interface LoginView {
    void navigateToMain();
    void showLoadingIndicator();
    void hideLoadingIndicator();
    void showError(String error);
    void resetMessage();
}
