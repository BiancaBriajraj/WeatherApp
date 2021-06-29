package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.example.weatherapp.model.WeatherInfo

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val myData = it.getParcelable<WeatherInfo>("currentWeatherInfo")
            binding.detailsCityName.text = myData?.name.toString()
            binding.apply {
                detailsMinTemp.append(myData?.main?.tempMin.toString())
                detailsMaxTemp.append(myData?.main?.tempMax.toString())
                detailsCityCode.append(myData?.cod.toString())
                detailTimeZone.append(myData?.timezone.toString())
                detailsSunrise.append(myData?.sys?.sunrise.toString())
                detailsSunrset.append(myData?.sys?.sunset.toString())
                for (i in 0..myData?.weather?.size!!-1){
                   val mModel = myData?.weather?.get(i)
                   val weatherDetails = "${mModel?.main} , ${mModel.description}"
                   detailsWeaterTitle.append(weatherDetails)
                 }
                detailsFeelLike.append(myData?.main?.feelsLike.toString())
                detailsPressure.append(myData?.main?.pressure.toString())
                detailsHumidity.append(myData?.main?.humidity.toString())
                detailsSpeed.append(myData?.wind?.speed.toString())
                detailsGust.append(myData?.wind?.gust.toString())
                detailsDeg.append(myData?.wind?.deg.toString())
            }
        }
    }
}