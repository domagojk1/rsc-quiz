package com.egzepsn.rsc.rscapp.rest;

import com.egzepsn.rsc.rscapp.models.BaseReponse;
import com.egzepsn.rsc.rscapp.models.LoginRequest;
import com.egzepsn.rsc.rscapp.models.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/Users/login")
    Call<Boolean> login(@Body LoginRequest loginRequest);
}

