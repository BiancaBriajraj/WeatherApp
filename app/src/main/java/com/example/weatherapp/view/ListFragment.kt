package com.example.weatherapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentListBinding
import com.example.weatherapp.model.CustomCallback
import com.example.weatherapp.model.WeatherInfo
import com.example.weatherapp.viewModel.ItemAdapter
import com.example.weatherapp.viewModel.WeatherViewModel


class ListFragment : Fragment() {

    private  lateinit var binding: FragmentListBinding

    private lateinit var viewModel: WeatherViewModel

    private var listAdapter = ItemAdapter(arrayListOf())

      override  fun onCreateView(
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

            binding.searchText.setText(" ")

            viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

            binding.searchButton.setOnClickListener {
                val userInput = binding.searchText.text.toString()
                viewModel.getInformation(userInput,object : CustomCallback {
                    override fun onSuccess(value: WeatherInfo) {
                        val v1=  value
                        binding.testingId.text = value.name
                    }

                    override fun onFailure(message: String) {
                        binding.testingId.text = message
                    }
                })

            }
            binding.floatingActionButton.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_listFragment_to_detailsFragment)
            }
        }



    }





