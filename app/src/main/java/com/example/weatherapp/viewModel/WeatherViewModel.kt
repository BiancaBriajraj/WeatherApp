package com.example.weatherapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    private val _list  = MutableLiveData<WeatherInfo>()
    val list: LiveData<WeatherInfo> get() = _list

    val loading by lazy { MutableLiveData<Boolean>() }

    private val BASE_URL ="https://api.openweathermap.org/"


    fun getInformation(user: String, callback: CustomCallback){
      retrieveList(user,callback)
     }

    private fun retrieveList(user:String, cb: CustomCallback){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        loading.value = true

        val apiService = retrofit.create(ApiService::class.java)
        val mCall : Call<WeatherInfo> = apiService.getInfo(user, "38c00111afac170f911d0425f161f93a")

        mCall.enqueue(object:Callback<WeatherInfo> {
            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                if(response.body()!!.cod ==200){
                    _list.value = response.body()!!
                    loading.value =false
                    cb.onSuccess(response.body()!!)
                }else{
                    cb.onFailure(response.body()!!.message)
                }


            }

            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
               Log.e("Error", t.message.toString())
                loading.value =false
            }

        })

    }

}