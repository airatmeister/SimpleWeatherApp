package com.weather.favorite.data

import com.weather.favorite.data.cache.CacheDataSource
import com.weather.favorite.data.mapper.DataToDomainMapper
import com.weather.favorite.domain.repository.SavedCitiesRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SavedCitiesRepositoryImpl @Inject constructor(
    private val cacheDataSource: CacheDataSource,
    private val mapper: DataToDomainMapper
    ): SavedCitiesRepository {

    override fun getCities() =
        cacheDataSource.getCities().map { cityEntity ->
            cityEntity.map {
                mapper.map(it)
            }
        }

    override fun deleteCity(city: String) = cacheDataSource.deleteCity(city)
}