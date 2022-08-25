package com.weather.favorite.data.cache

import com.weatherapp.core.data.db.CityDao
import com.weatherapp.core.data.db.CityEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Test

class CacheDataSourceTest {

    @Test
    fun test_call_CacheDataSource_getCities(){
        val cityDao = MockCityDao()
        CacheDataSource.Base(cityDao).getCities()
        Assert.assertEquals(cityDao.isCalled, true)
    }

    @Test
    fun test_call_CacheDataSource_deleteCity(){
        val cityDao = MockCityDao()
        CacheDataSource.Base(cityDao).deleteCity("Moscow")
        Assert.assertEquals(cityDao.isCalled, true)
    }

    class MockCityDao: CityDao {

        var isCalled = false

        override suspend fun saveCity(city: CityEntity) = Unit

        override fun getSavedCities(): Flow<List<CityEntity>> {
            isCalled = true
            return flow { emit(mutableListOf()) }
        }

        override fun deleteByCity(city: String) {
            isCalled = true
        }
    }
}