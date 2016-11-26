package com.egzepsn.rsc.rscapp.modules.initial.register.interactor;

import android.util.Log;

import com.egzepsn.rsc.rscapp.helpers.ErrorParser;
import com.egzepsn.rsc.rscapp.models.BaseReponse;
import com.egzepsn.rsc.rscapp.models.ErrorField;
import com.egzepsn.rsc.rscapp.models.RegisterRequest;
import com.egzepsn.rsc.rscapp.commons.listeners.FinishedListener;
import com.egzepsn.rsc.rscapp.rest.ApiModule;
import com.egzepsn.rsc.rscapp.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by domagoj on 11/23/16.
 */

public class RegisterInteractorImpl implements RegisterInteractor {
    @Override
    public void register(String username, String password, String confirmPassword, final FinishedListener listener) {
        ApiService apiService = ApiModule.createService(ApiService.class);
        Call<BaseReponse> call = apiService.register(new RegisterRequest(username, password, confirmPassword));

        call.enqueue(new Callback<BaseReponse>() {
            @Override
            public void onResponse(Call<BaseReponse> call, Response<BaseReponse> response) {
                BaseReponse baseReponse = response.body();

                if (baseReponse != null && baseReponse.getToken() != null && baseReponse.getTokenExpires() != null) {
                    Log.d("REGISTER", "SUCCESS");
                    listener.onSuccess();
                }
                else {
                    BaseReponse errorResponse = ErrorParser.parse(response, BaseReponse.class);

                    if (errorResponse != null) {
                        Log.d("REGISTER", errorResponse.getMessage());
                        for (ErrorField error : errorResponse.getErrors()) {
                            Log.d("REGISTER", error.getField() + ": " + error.getMessage());
                        }
                        listener.onError(errorResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseReponse> call, Throwable t) {
                Log.v("REGISTER", "FAIL");
                listener.onError("ERR");
            }
        });
    }
}
