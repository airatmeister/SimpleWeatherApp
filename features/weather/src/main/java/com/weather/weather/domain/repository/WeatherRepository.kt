package com.weather.weather.domain.repository

import com.weather.weather.domain.model.WeatherItem
import com.weatherapp.core.common.Result
import kotlinx.coroutines.flow.StateFlow

interface WeatherRepository {

    val mutableStateFlow: StateFlow<Result<WeatherItem>>

    suspend fun fetchWeather(city: String)

    suspend fun saveCity(city: Pair<String, String>)
}