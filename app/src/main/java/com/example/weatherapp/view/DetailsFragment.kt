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
            myData?.apply {
                binding.detailsCityName.text = myData.name
                binding.apply {
                    detailsLatitude.text = getString(R.string.latitude, coord.lat.toString())
                    detailsLongitude.text = getString(R.string.longitude, coord.lon.toString())
                    detailsMinTemp.text = getString(R.string.mini_temp, convertToCelsuis(main.tempMin).toString())
                    detailsMaxTemp.text = getString(R.string.max_temp, convertToCelsuis(main.tempMax).toString())
                    detailsFeelLike.text = getString(R.string.feels_like, convertToCelsuis(main.feelsLike).toString())
                    detailsPressure.append(" ${main.pressure}%")
                    detailsHumidity.append(" ${main.humidity}hPa")
                    detailsCityCode.append(" $cod")
                    detailTimeZone.append(" $timezone")
                    detailsSunrise.append(" ${sys.sunrise}")
                    detailsSunrset.append(" ${sys.sunset}")
                    weather.forEach { wObj ->
                        val weatherDetails = " ${wObj.main}, ${wObj.description}"
                        detailsWeaterTitle.append(weatherDetails)
                    }
                    detailsSpeed.text = getString(R.string.speed, wind.speed.toString())
                    detailsGust.text = getString(R.string.gust, wind.gust.toString())
                    detailsDeg.text = getString(R.string.degree, wind.deg.toString())
                }
            }

        }
    }
    private fun convertToCelsuis(kelvin: Double): Int = (kelvin - 281.86).roundToInt()
}