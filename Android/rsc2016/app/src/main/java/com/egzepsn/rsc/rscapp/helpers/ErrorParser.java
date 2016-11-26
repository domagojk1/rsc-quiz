package com.egzepsn.rsc.rscapp.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

import retrofit2.Response;

/**
 * Created by mario on 24.11.2016..
 */

public class ErrorParser {
    public static <T extends Serializable> T parse(Response<T> response, Class<T> tClass) {
        if (response != null && response.body() == null && response.errorBody() != null) {
            Gson gson = new GsonBuilder().create();
            try {
                T errorResponse = gson.fromJson(response.errorBody().string(), tClass);
                return errorResponse;
            } catch (Exception ex) {
                return null;
            }
        } else {
            return null;
        }
    }
}
