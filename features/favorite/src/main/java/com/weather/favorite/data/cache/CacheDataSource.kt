package com.weather.favorite.data.cache

import com.weatherapp.core.data.db.CityDao
import com.weatherapp.core.data.db.CityEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CacheDataSource {

    fun getCities(): Flow<List<CityEntity>>

    fun deleteCity(city: String)

    class Base @Inject constructor(private val cityDao: CityDao): CacheDataSource{

        override fun getCities() = cityDao.getSavedCities()

        override fun deleteCity(city: String) = cityDao.deleteByCity(city)
    }
}