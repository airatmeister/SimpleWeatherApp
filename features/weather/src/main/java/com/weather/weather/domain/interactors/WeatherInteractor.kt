package com.weather.weather.domain.interactors

import android.util.Log
import com.weather.weather.domain.model.WeatherItem
import com.weather.weather.domain.repository.WeatherRepository
import com.weatherapp.core.common.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface WeatherInteractor {

    fun executeSearchCity(city: String): Flow<Result<WeatherItem>>
    suspend fun saveCity(city: String, weather: String)

    class Base @Inject constructor(
        private val weatherRepository: WeatherRepository
        ) : WeatherInteractor{

        override fun executeSearchCity(city: String): Flow<Result<WeatherItem>> {
            return flow {
                weatherRepository.fetchWeather(city)
                weatherRepository.mutableStateFlow.collect { emit(it) }
            }
                .onStart {
                    emit(Result.Loading())
                }
                .flowOn(Dispatchers.IO)
                .catch { emit(Result.Error(it.message.toString())) }
        }

        override suspend fun saveCity(city: String, weather: String) =
            withContext(Dispatchers.IO){
                weatherRepository.saveCity(Pair(city, weather))
            }
    }
}