package com.weather.weather.data.cache

import com.weatherapp.core.data.db.CityDao
import com.weatherapp.core.data.db.CityEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CacheDataSourceTest {

    @Test
    fun test_call_CityDao_saveCity() = runBlocking{
        val cityDao = MockCityDao()
        CacheDataSource.Base(cityDao).saveCity(Pair("Moscow", "30"))
        Assert.assertEquals(cityDao.isCalled, true)
    }

    class MockCityDao: CityDao {

        var isCalled = false

        override suspend fun saveCity(city: CityEntity) {
            isCalled = true
        }

        override fun getSavedCities(): Flow<List<CityEntity>> = flow { emit(mutableListOf()) }

        override fun deleteByCity(city: String) = Unit

    }
}