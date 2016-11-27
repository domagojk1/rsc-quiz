package com.egzepsn.rsc.rscapp.rest;

import com.egzepsn.rsc.rscapp.models.BaseReponse;
import com.egzepsn.rsc.rscapp.models.CreateTeam;
import com.egzepsn.rsc.rscapp.models.CreateTeamResponse;
import com.egzepsn.rsc.rscapp.models.Event;
import com.egzepsn.rsc.rscapp.models.JoinTeam;
import com.egzepsn.rsc.rscapp.models.JoinTeamResponse;
import com.egzepsn.rsc.rscapp.models.LoginRequest;
import com.egzepsn.rsc.rscapp.models.RegisterRequest;
import com.facebook.CallbackManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/Users/login")
    Call<BaseReponse> login(@Body LoginRequest loginRequest);

    @GET("api/Events/")
    Call<ArrayList<Event>> getEvents();

    @POST("api/Teams/Create")
    Call<CreateTeamResponse> createTeam(@Body CreateTeam createTeam);

    @POST("api/Teams/join")
    Call<JoinTeamResponse> joinTeam(@Body JoinTeam joinTeam);
}

