package com.egzepsn.rsc.rscapp.models;

import com.egzepsn.rsc.rscapp.app.RSCApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class BaseReponse implements Serializable {

    @SerializedName("token")
    private String token;

    @SerializedName("tokenExpires")
    private String tokenExpires;

    @SerializedName("errors")
    ArrayList<ErrorField> errors;

    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private int code;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenExpires() {
        return tokenExpires;
    }

    public void setTokenExpires(String tokenExpires) {
        this.tokenExpires = tokenExpires;
    }

    public ArrayList<ErrorField> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<ErrorField> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
