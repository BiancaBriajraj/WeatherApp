package com.example.weatherapp.model

interface CustomCallback {
    fun onSuccess(value: WeatherInfo)
    fun onFailure(code: Int,message: String)
}