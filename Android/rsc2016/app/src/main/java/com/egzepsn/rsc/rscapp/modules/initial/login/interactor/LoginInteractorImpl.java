package com.egzepsn.rsc.rscapp.modules.initial.login.interactor;

import android.util.Log;

import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.egzepsn.rsc.rscapp.commons.listeners.FinishedListener;
import com.egzepsn.rsc.rscapp.enums.AppStateEnum;
import com.egzepsn.rsc.rscapp.models.BaseReponse;
import com.egzepsn.rsc.rscapp.models.LoginRequest;
import com.egzepsn.rsc.rscapp.models.User;
import com.egzepsn.rsc.rscapp.rest.ApiModule;
import com.egzepsn.rsc.rscapp.rest.ApiService;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor {
    @Override
    public void loginFacebook(final FinishedListener listener, CallbackManager manager, LoginButton button) {
        Log.d("INTERACTOR","LOGIN");

        button.registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String token = loginResult.getAccessToken().getToken();
                Log.d("ONSUCCESS", token);
                sendToken(token);
                listener.onSuccess();
            }

            @Override
            public void onCancel() {
                listener.onCancel();
            }

            @Override
            public void onError(FacebookException error) {
                listener.onError(error.getMessage());
            }
        });
    }

    private void sendToken(String token) {
        ApiService service = ApiModule.createService(ApiService.class);
        Call<BaseReponse> call = service.login(new LoginRequest(token));
        Log.d("SEND","TOKEN");

        call.enqueue(new Callback<BaseReponse>() {
            @Override
            public void onResponse(Call<BaseReponse> call, Response<BaseReponse> response) {
                BaseReponse result = response.body();

                if (result != null) {
                    Log.d("ONRESPONSE", response.body().getToken() + "");
                }
            }

            @Override
            public void onFailure(Call<BaseReponse> call, Throwable t) {
                Log.d("ONFAILURE", t.getMessage());
            }
        });
    }
}
