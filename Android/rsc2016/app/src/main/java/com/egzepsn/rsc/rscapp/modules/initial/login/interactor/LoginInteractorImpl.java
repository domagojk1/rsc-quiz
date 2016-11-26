package com.egzepsn.rsc.rscapp.modules.initial.login.interactor;

import android.util.Log;


import com.egzepsn.rsc.rscapp.R;
import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.helpers.ErrorParser;
import com.egzepsn.rsc.rscapp.models.BaseReponse;
import com.egzepsn.rsc.rscapp.models.ErrorField;
import com.egzepsn.rsc.rscapp.models.LoginRequest;
import com.egzepsn.rsc.rscapp.models.User;
import com.egzepsn.rsc.rscapp.commons.listeners.FinishedListener;
import com.egzepsn.rsc.rscapp.rest.ApiModule;
import com.egzepsn.rsc.rscapp.rest.ApiService;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void login(FinishedListener listener, CallbackManager manager, LoginButton button) {

    }

    /*
    private void saveUserData(String username, String password, String token) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setToken(token);
        RSCApp.setLoggedUser(user);
    }
    */
}
