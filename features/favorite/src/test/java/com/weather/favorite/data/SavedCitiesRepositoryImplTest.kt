package com.weather.favorite.data

import com.weather.favorite.data.cache.CacheDataSource
import com.weather.favorite.data.mapper.DataToDomainMapper
import com.weatherapp.core.data.db.CityEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SavedCitiesRepositoryImplTest {

    @Test
    fun test_call_CacheDataSource_getCities(){
        val cacheDataSource = MockCacheDataSource()
        val mapper = DataToDomainMapper.Base()
        SavedCitiesRepositoryImpl(cacheDataSource, mapper).getCities()
        Assert.assertEquals(cacheDataSource.isCalled, true)
    }

    @Test
    fun test_call_CacheDataSource_deleteCity(){
        val cacheDataSource = MockCacheDataSource()
        val mapper = DataToDomainMapper.Base()
        SavedCitiesRepositoryImpl(cacheDataSource, mapper).deleteCity("Moscow")
        Assert.assertEquals(cacheDataSource.isCalled, true)
    }

    @Test
    fun test_map() = runBlocking{
        val cacheDataSource = MockGetCitiesCacheDataSource()
        val mapper = DataToDomainMapper.Base()
        SavedCitiesRepositoryImpl(cacheDataSource, mapper).getCities().collect {
            it[0].let { city ->
                Assert.assertEquals("${city.city} ${city.weather}", "Moscow 30")
            }
        }
    }

    class MockCacheDataSource: CacheDataSource {

        var isCalled = false

        override fun getCities(): Flow<List<CityEntity>> {
            isCalled = true
            return flow { emit(mutableListOf()) }
        }

        override fun deleteCity(city: String) {
            isCalled = true
        }
    }

    class MockGetCitiesCacheDataSource: CacheDataSource {

        override fun getCities() =
            flow {
                emit(
                    mutableListOf(
                        CityEntity(
                            id = 0,
                            city = "Moscow",
                            weather = "30"
                        )
                    )
                )
            }

        override fun deleteCity(city: String) = Unit
    }
}