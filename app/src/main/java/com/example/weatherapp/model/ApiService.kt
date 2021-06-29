package com.example.weatherapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather?")
    fun getInfo( @Query("q")q: String ,@Query("appid")appid: String ): Call<WeatherInfo>
}