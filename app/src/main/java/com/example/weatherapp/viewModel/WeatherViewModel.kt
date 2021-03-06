package com.example.weatherapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.ApiService
import com.example.weatherapp.model.WeatherInfo
import com.example.weatherapp.model.CustomCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel:ViewModel() {

    private val BASE_URL ="https://api.openweathermap.org/"
    private val API_KEY = "38c00111afac170f911d0425f161f93a"

    fun getInformation(user: String, callback: CustomCallback){
      retrieveList(user,callback)
     }

    private fun retrieveList(user:String, cb: CustomCallback){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        val mCall : Call<WeatherInfo> = apiService.getInfo(user, API_KEY)
        mCall.enqueue(object:Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                if (!response.isSuccessful ) {
                    cb.onFailure(response.code(), response.message())
                }else {
                    cb.onSuccess(response.body()!!)
                }
           }
            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
               Log.e("Error", t.message.toString())
                cb.onFailure(0, "Failed to retrieve information" )
            }
        })
    }
}