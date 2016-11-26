package com.egzepsn.rsc.rscapp.modules.initial.register.view;

/**
 * Created by domagoj on 11/23/16.
 */

public interface RegisterView {
    void navigateToLogin();
    void showError(String error);
    void showLoadingIndicator();
    void hideLoadingIndicator();
    void resetMessage();
}
