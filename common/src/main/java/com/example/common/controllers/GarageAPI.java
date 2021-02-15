package com.example.common.controllers;

import com.example.common.entities.Garage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GarageAPI {
    @GET("WypPzJCt")
    Call<Garage> loadGarage();
}
