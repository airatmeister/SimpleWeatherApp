package com.weather.favorite.domain.repository

import com.weather.favorite.domain.model.City
import kotlinx.coroutines.flow.Flow

interface SavedCitiesRepository {

    fun getCities(): Flow<List<City>>

    fun deleteCity(city: String)
}