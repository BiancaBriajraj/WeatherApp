package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.example.weatherapp.model.WeatherInfo

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
                    detailsMinTemp.text = getString(R.string.mini_temp, myData.main.tempMin.toString())
                    detailsMaxTemp.append(" ${myData.main.tempMax}")
                    detailsFeelLike.append(" ${myData.main.feelsLike}")
                    detailsPressure.append(" ${myData.main.pressure}")
                    detailsHumidity.append(" ${myData.main.humidity}")
                    detailsCityCode.append(" ${myData.cod}")
                    detailTimeZone.append(" ${myData.timezone}")
                    detailsSunrise.append(" ${myData.sys.sunrise}")
                    detailsSunrset.append(" ${myData.sys.sunset}")
                    for (i in 0 .. myData.weather.size.minus(1)){
                        val mModel = myData.weather[i]
                        val weatherDetails = "${mModel.main}, ${mModel.description}"
                        detailsWeaterTitle.append(weatherDetails)
                    }
                    detailsSpeed.append(" ${myData.wind.speed}")
                    detailsGust.append(" ${myData.wind.gust}")
                    detailsDeg.append(" ${myData.wind.deg}")
                }
            }

        }
    }
}