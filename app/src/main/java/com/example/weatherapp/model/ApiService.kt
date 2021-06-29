package com.example.weatherapp.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

   // https://api.openweathermap.org/data/2.5/weather?q=London&appid=38c00111afac170f911d0425f161f93a

    @GET("data/2.5/weather?")
    fun getInfo( @Query("q")q: String ,@Query("appid")appid: String ): Call<WeatherInfo>
}