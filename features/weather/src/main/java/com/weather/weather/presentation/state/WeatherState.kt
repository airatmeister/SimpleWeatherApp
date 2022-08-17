package com.weather.weather.presentation.state

import androidx.core.view.isVisible
import com.weather.weather.databinding.FragmentWeatherBinding
import com.weather.weather.presentation.adapter.WeatherAdapter
import com.weather.weather.presentation.model.WeatherItem

interface WeatherState {

    fun apply(binding: FragmentWeatherBinding, weatherAdapter: WeatherAdapter)

    class Success(private val listItems: List<WeatherItem>): WeatherState{
        override fun apply(binding: FragmentWeatherBinding, weatherAdapter: WeatherAdapter) = with(binding) {
            recyclerViewWeather.isVisible = true
            weatherAdapter.submitList(listItems)
            loadingWeather.isVisible = false
            errorMessage.isVisible = false
        }
    }

    class Error(private val message: String): WeatherState {

        override fun apply(binding: FragmentWeatherBinding, weatherAdapter: WeatherAdapter) = with(binding) {
            recyclerViewWeather.isVisible = false
            loadingWeather.isVisible = false
            errorMessage.isVisible = true
            errorMessage.text = message
        }
    }

    class Loading: WeatherState {

        override fun apply(binding: FragmentWeatherBinding, weatherAdapter: WeatherAdapter) = with(binding) {
            recyclerViewWeather.isVisible = false
            loadingWeather.isVisible = true
            errorMessage.isVisible = false
        }
    }
}