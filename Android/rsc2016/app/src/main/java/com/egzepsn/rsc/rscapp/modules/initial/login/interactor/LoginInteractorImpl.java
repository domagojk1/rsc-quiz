package com.egzepsn.rsc.rscapp.modules.initial.login.interactor;

import android.util.Log;

import com.egzepsn.rsc.rscapp.commons.listeners.FinishedListener;
import com.egzepsn.rsc.rscapp.models.LoginRequest;
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
    public void login(final FinishedListener listener, CallbackManager manager, LoginButton button) {
        Log.d("INTERACTOR","LOGIN");

        button.registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String token = loginResult.getAccessToken().getToken();
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
        Call<Boolean> call = service.login(new LoginRequest(token));
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean result = response.body();
                Log.d("ONRESPONSE", result + "");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("ONFAILURE", t.getMessage());
            }
        });
    }
}
