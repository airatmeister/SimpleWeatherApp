package com.weather.favorite.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.weather.favorite.domain.interactor.CitiesInteractor
import com.weather.favorite.presentation.mapper.DomainToUiMapper
import com.weather.favorite.presentation.model.WeatherItem
import com.weatherapp.core.common.Dispatchers
import com.weatherapp.core.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val citiesInteractor: CitiesInteractor,
    private val mapper: DomainToUiMapper,
    private val dispatchers: Dispatchers
    ) : BaseViewModel() {

    private val _mutableSavedCities: MutableLiveData<List<WeatherItem>> = MutableLiveData()
    val savedCities: LiveData<List<WeatherItem>> = _mutableSavedCities

    init {
        dispatchers.launchUI(viewModelScope) {
            mapper.map(citiesInteractor.getSavedCities()).collect {
                _mutableSavedCities.value = it
            }
        }
    }

    fun deleteCity(city: String) =
        dispatchers.launchUI(viewModelScope) {
            citiesInteractor.deleteCity(city)
        }
}