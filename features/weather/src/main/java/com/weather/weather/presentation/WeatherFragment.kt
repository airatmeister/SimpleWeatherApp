package com.weather.weather.presentation

import android.os.Bundle
import android.widget.Toast
import com.weather.weather.R
import com.weather.weather.databinding.FragmentWeatherBinding
import com.weather.weather.presentation.adapter.WeatherAdapter
import com.weatherapp.core.plugins.viewbinding.viewBinding
import com.weatherapp.core.presentation.base.BaseFragment
import javax.inject.Inject

class WeatherFragment : BaseFragment(R.layout.fragment_weather) {

    private val binding by viewBinding(FragmentWeatherBinding::bind)
    private val viewModel: WeatherViewModel by settingsViewModels()

    override fun callOperations() = Unit

    @Inject lateinit var weatherAdapter: WeatherAdapter

    override fun onSetupLayout(savedInstanceState: Bundle?) = with(binding) {
        weatherAdapter.onClick = { city, weather ->
            viewModel.saveCity(city, weather)
            Toast.makeText(requireContext().applicationContext, "City added", Toast.LENGTH_LONG).show()
        }
        buttonSearch.setOnClickListener {
            viewModel.searchCity(searchWeatherText.text.toString())
        }
        recyclerViewWeather.adapter = weatherAdapter
    }

    override fun onBindViewModel() = with(viewModel) {
        weather.observe {
            it.apply(binding, weatherAdapter)
        }
    }
}