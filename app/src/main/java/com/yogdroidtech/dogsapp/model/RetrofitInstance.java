package com.yogdroidtech.dogsapp.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static Retrofit INSTANCE = null;
    public static Retrofit getInstance(){
        if(INSTANCE==null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/DevTides/DogsApi/master/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
