package com.example.weatherapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherInfo(
    val coord: Coord,
    val weather: ArrayList<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int,
    val message: String) :Parcelable

@Parcelize
data class Coord(
    val lon: Double,
    val lat: Double ) :Parcelable

@Parcelize
data class Weather(
    val id: Int,
    val main: String,
    val description:String,
    val icon:String ) :Parcelable

@Parcelize
data class Main(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int ) :Parcelable

@Parcelize
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double) :Parcelable

@Parcelize
data class Clouds(
    val all: Int ):Parcelable

@Parcelize
data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int):Parcelable





