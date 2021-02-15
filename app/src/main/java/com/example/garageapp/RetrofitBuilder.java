package com.example.garageapp;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitBuilder {
    public static final String BASE_URL = "https://pastebin.com/raw/";
    private final static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();


    public static RetrofitService getGarageService() {
        return retrofit.create(RetrofitService.class);
    }

}
