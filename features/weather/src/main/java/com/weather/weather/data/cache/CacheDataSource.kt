package com.weather.weather.data.cache

import com.weatherapp.core.data.db.CityDao
import com.weatherapp.core.data.db.CityEntity
import javax.inject.Inject

interface CacheDataSource {

    suspend fun saveCity(city: Pair<String, String>)

    class Base @Inject constructor(private val cityDao: CityDao): CacheDataSource {
        override suspend fun saveCity(city: Pair<String, String>) {
            city.let { (city, weather) ->
                cityDao.saveCity(CityEntity(city= city, weather = weather))
            }
        }
    }
}