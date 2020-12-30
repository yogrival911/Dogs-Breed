package com.yogdroidtech.dogsapp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("dogs.json")
    Call<List<Dog>> getDogs();

}
