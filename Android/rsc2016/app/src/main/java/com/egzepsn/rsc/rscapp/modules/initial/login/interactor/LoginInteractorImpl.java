package com.egzepsn.rsc.rscapp.modules.initial.login.interactor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

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
                final String token = loginResult.getAccessToken().getToken();
                sendToken(token);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject();
                                    Log.d("ONCOMPLETED",jsonObject + "");
                                    User user = new User();
                                    user.setToken(token);
                                    user.setUsername(jsonObject.getString("email"));
                                    user.setUserPicture("https://graph.facebook.com/" + jsonObject.getString("id") + "/picture?type=large");
                                    RSCApp.setLoggedUser(user);

                                    SharedPreferences.Editor editor = RSCApp.getInstance().getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE).edit();
                                    editor.putString("userPicture", RSCApp.getLoggedUser().getUserPicture());
                                    editor.putString("userEmail", RSCApp.getLoggedUser().getUsername());
                                    editor.apply();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        });

                Bundle params = new Bundle();
                params.putString("fields", "id,email,gender,cover,picture.type(large)");
                request.setParameters(params);
                request.executeAsync();

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
