package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.example.weatherapp.model.WeatherInfo
import kotlin.math.roundToInt

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val myData = it.getParcelable<WeatherInfo>("currentWeatherInfo")
            if (myData != null){
                binding.detailsCityName.text = myData.name
                binding.apply {
                    detailsLatitude.text = getString(R.string.latitude, myData.coord.lat.toString())
                    detailsLongitude.text = getString(R.string.longitude, myData.coord.lon.toString())
                    detailsMinTemp.text = getString(R.string.mini_temp, convertToCelsuis(myData.main.tempMin).toString())
                    detailsMaxTemp.text = getString(R.string.max_temp, convertToCelsuis(myData.main.tempMax).toString())
                    detailsFeelLike.text = getString(R.string.feels_like, convertToCelsuis(myData.main.feelsLike).toString())
                    detailsPressure.append(" ${myData.main.pressure}%")
                    detailsHumidity.append(" ${myData.main.humidity}hPa")
                    detailsCityCode.append(" ${myData.cod}")
                    detailTimeZone.append(" ${myData.timezone}")
                    detailsSunrise.append(" ${myData.sys.sunrise}")
                    detailsSunrset.append(" ${myData.sys.sunset}")
                    for (i in 0 .. myData.weather.size.minus(1)){
                        val mModel = myData.weather[i]
                        val weatherDetails = " ${mModel.main}, ${mModel.description}"
                        detailsWeaterTitle.append(weatherDetails)
                    }
                    detailsSpeed.text = getString(R.string.speed, myData.wind.speed.toString())
                    detailsGust.text = getString(R.string.gust, myData.wind.gust.toString())
                    detailsDeg.text = getString(R.string.degree, myData.wind.deg.toString())
                }
            }

        }
    }
    private fun convertToCelsuis(kelvin :Double) : Int = (kelvin-281.86).roundToInt()
}