package com.example.weatherapp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.weatherapp.databinding.FragmentListBinding
import com.example.weatherapp.model.CustomCallback
import com.example.weatherapp.model.WeatherInfo
import com.example.weatherapp.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        binding.searchButton.setOnClickListener {

            binding.searchLayout.visibility = View.GONE
            binding.errorLayout.visibility = View.GONE
            val userInput = binding.searchText.text.toString()
            binding.progressBarLoading.visibility = View.VISIBLE
            viewModel.getInformation(userInput, object : CustomCallback {
                override fun onSuccess(value: WeatherInfo) {
                    binding.progressBarLoading.visibility = View.GONE
                    binding.searchLayout.visibility = View.VISIBLE
                    binding.cityNameSearchLayoutView.text = value.name
                    binding.minTempSearchLayoutView.append(value.main.tempMin.toString())
                    binding.maxTempSearchLayoutView.append(value.main.tempMax.toString())

                    binding.moreInfoButton.setOnClickListener {
                        Navigation.findNavController(it).navigate(
                            ListFragmentDirections.actionListFragmentToDetailsFragment(value)
                        )
                    }
                }

                override fun onFailure(errorCode: Int, message: String) {
                    binding.progressBarLoading.visibility = View.GONE
                    binding.errorLayout.visibility = View.VISIBLE
                    binding.errorImageView.visibility = View.VISIBLE
                    binding.errorCodeView.append(errorCode.toString())
                    binding.errorMessgae.append(message)

                }
            })

        }

    }
}





