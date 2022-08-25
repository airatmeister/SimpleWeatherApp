package com.weather.weather.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.weather.weather.domain.interactors.WeatherInteractor
import com.weather.weather.presentation.mapper.DomainToUiMapper
import com.weather.weather.presentation.model.WeatherItem
import com.weather.weather.presentation.state.WeatherState
import com.weatherapp.core.common.Dispatchers
import com.weatherapp.core.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.weatherapp.core.common.Result

class WeatherViewModel @Inject constructor(
    private val weatherInteractor: WeatherInteractor,
    private val mapper: DomainToUiMapper,
    private val dispatchers: Dispatchers
    ): BaseViewModel() {

    private val _mutableWeather: MutableLiveData<WeatherState> = MutableLiveData()
    val weather: LiveData<WeatherState> = _mutableWeather

    private var _mutableListWeather = mutableListOf<WeatherItem>()

    fun searchCity(city: String) {
        dispatchers.launchUI(viewModelScope) {
            _mutableWeather.value = WeatherState.Loading()
            weatherInteractor.executeSearchCity(city).collect {
                when(it){
                    is Result.Success -> {
                        val weatherItem = mapper.map(it.data)
                        if (
                            _mutableListWeather.isEmpty() ||
                            _mutableListWeather.last() != weatherItem
                        ) _mutableListWeather.add(weatherItem)
                        _mutableWeather.value = WeatherState.Success(_mutableListWeather)
                    }
                    is Result.Error -> _mutableWeather.value = WeatherState.Error(it.message)
                    is Result.Loading -> _mutableWeather.value = WeatherState.Loading()
                }
            }
        }
    }

    fun saveCity(city: String, weather: String) =
        dispatchers.launchUI(viewModelScope) {
            weatherInteractor.saveCity(city, weather)
        }
}