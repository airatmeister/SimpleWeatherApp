package com.weather.favorite.domain.interactor

import com.weather.favorite.domain.model.City
import com.weather.favorite.domain.repository.SavedCitiesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CitiesInteractor {

    fun getSavedCities(): Flow<List<City>>

    suspend fun deleteCity(city: String)

    class Base @Inject constructor(private val savedCitiesRepository: SavedCitiesRepository): CitiesInteractor {

        override fun getSavedCities() = savedCitiesRepository.getCities()

        override suspend fun deleteCity(city: String) =
            withContext(Dispatchers.IO) {
                savedCitiesRepository.deleteCity(city)
            }
    }
}